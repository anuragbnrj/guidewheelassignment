package in.anuragbanerjee.guidewheelbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import in.anuragbanerjee.guidewheelbackend.data.MetricDetail;
import in.anuragbanerjee.guidewheelbackend.data.Row;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class MachineDataService {
    private final String key = "Psum";

    public Map<LocalDateTime, Double> getPowerDistributionTimeSeries(LocalDateTime fromLocalDateTime) throws CsvValidationException, IOException {
        List<Row> rowList = readCSVData();

//        Double operatingLoad = calculateOperatingLoad(rowList);

        Map<LocalDateTime, Row> mappedRows = mapRowsByLocalDateTime(rowList);

        int timeInterval = 720; // 3 hrs data for brevity
        Map<LocalDateTime, Double> powerDistributionTimeSeries = new TreeMap<>();
        for (LocalDateTime curr = fromLocalDateTime; curr.isBefore(fromLocalDateTime.plusMinutes(timeInterval)); curr = curr.plusMinutes(1)) {
            Row row = mappedRows.get(curr);
            if (row != null) {
                String metrics = row.getMetrics();
                MetricDetail metricDetail = extractJsonValue(metrics, key, MetricDetail.class);

                if (metricDetail != null && metricDetail.getAvgvalue()!= null) {
                    powerDistributionTimeSeries.put(curr, metricDetail.getAvgvalue());
                } else {
                    powerDistributionTimeSeries.put(curr, 0.0);
                }
            } else {
                powerDistributionTimeSeries.put(curr, 0.0);
            }
        }

        return powerDistributionTimeSeries;
    }

    public Map<String, Integer> getPowerDistributionInTimeRange(LocalDateTime fromLocalDateTime,
                                                                LocalDateTime toLocalDateTime) throws CsvValidationException, IOException {
        List<Row> rowList = readCSVData();

        Double operatingLoad = calculateOperatingLoad(rowList);
        log.info("Operating Load: {}", operatingLoad);

        Map<LocalDateTime, Row> mappedRows = mapRowsByLocalDateTime(rowList);

        return getPowerDistribution(fromLocalDateTime, toLocalDateTime, mappedRows, operatingLoad);
    }

    public Map<String, Integer> getPowerDistribution(LocalDateTime from, LocalDateTime to,
                                                     Map<LocalDateTime, Row> mappedRows, double operatingLoad) {
        int off = 0;
        int unloaded = 0;
        int idle = 0;
        int loaded = 0;
        for (LocalDateTime curr = from; curr.isBefore(to.plusMinutes(1)); curr = curr.plusMinutes(1)) {
            Row row = mappedRows.get(curr);
            if (row != null) {
                String metrics = row.getMetrics();
                MetricDetail metricDetail = extractJsonValue(metrics, key, MetricDetail.class);

                if (metricDetail != null && metricDetail.getAvgvalue() != null) {
                    double avgValue = metricDetail.getAvgvalue();

                    if (avgValue <= 10) {
                        unloaded++;
                    } else if (avgValue <= 0.2 * operatingLoad) {
                        idle++;
                    } else {
                        loaded++;
                    }
                } else {
                    off++;
                }
            } else {
                off++;
            }
        }

        Map<String, Integer> powerDistribution = new HashMap<>();
        powerDistribution.put("off", off);
        powerDistribution.put("unloaded", unloaded);
        powerDistribution.put("idle", idle);
        powerDistribution.put("loaded", loaded);
        log.info("Off: {} On(Unloaded): {} On(Idle): {} On(loaded): {}", off, unloaded, idle, loaded);
        return powerDistribution;
    }

    public Map<LocalDateTime, Row> mapRowsByLocalDateTime(List<Row> rowList) {
        Map<LocalDateTime, Row> mappedRows = new HashMap<>();

        for (Row row : rowList) {
            LocalDateTime from = row.getFromLocalDateTime();
            mappedRows.put(from, row);
        }

        return mappedRows;
    }

    public List<Row> readCSVData() throws IOException, CsvValidationException {
        List<Row> rowList = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:demoPumpDayData.csv")));
        String[] line;

        // Skip the header row
        reader.skip(1);

        LocalDateTime minLDT = LocalDateTime.of(2030, 1, 27, 4, 38, 0);
        LocalDateTime maxLDT = LocalDateTime.of(2000, 1, 27, 4, 38, 0);

        while ((line = reader.readNext()) != null) {
            Row row = new Row();
            row.setDeviceid(line[0]);
            row.setFromts(Long.valueOf(line[1]));
            row.setTots(Long.valueOf(line[2]));
            row.setMetrics(line[3]);

            ZoneId zoneId = ZoneId.of("UTC");

            LocalDateTime fromLocalDateTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(row.getFromts()),
                    zoneId
            );
            row.setFromLocalDateTime(fromLocalDateTime);

//            if (fromLocalDateTime.isBefore(minLDT)) {
//                minLDT = fromLocalDateTime;
//            }
//
//            if (fromLocalDateTime.isAfter(maxLDT)) {
//                maxLDT = fromLocalDateTime;
//            }

            LocalDateTime toLocalDateTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(row.getTots()),
                    zoneId
            );
            row.setToLocalDateTime(toLocalDateTime);

            rowList.add(row);
        }

        log.info("Min LDT: {}    Max LDT: {}", minLDT, maxLDT);

        reader.close();
        return rowList;
    }

    public Double calculateOperatingLoad(List<Row> rowList) {
        List<Double> psums = new ArrayList<>();
        for (Row row : rowList) {
            String metrics = row.getMetrics();
            MetricDetail metricDetail = extractJsonValue(metrics, key, MetricDetail.class);
            if (metricDetail != null && metricDetail.getAvgvalue() != null) {
                Double avgValue = metricDetail.getAvgvalue();
                psums.add(avgValue);
            }
        }

        // Calculate average of 10 greatest psum average values assuming there are always 10 values in the csv file
        psums.sort(Collections.reverseOrder());
        double operatingLoad = 0.0;
        for (int i = 0; i < 10; i++) {
            operatingLoad += psums.get(i);
        }
        operatingLoad /= 10;

        return operatingLoad;
    }

    private <T> T extractJsonValue(String metrics, String key, Class<T> classType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(metrics);
            JsonNode valueNode = rootNode.path(key);
            if (valueNode.isMissingNode()) {
                return null; // Key not found in JSON
            }
            return mapper.treeToValue(valueNode, classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Error occurred during JSON parsing
        }
    }

}
