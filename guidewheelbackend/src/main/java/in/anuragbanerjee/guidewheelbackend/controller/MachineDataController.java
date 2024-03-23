package in.anuragbanerjee.guidewheelbackend.controller;

import com.opencsv.exceptions.CsvValidationException;
import in.anuragbanerjee.guidewheelbackend.service.MachineDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MachineDataController {

    private final MachineDataService machineDataService;

    public MachineDataController(MachineDataService machineDataService) {
        this.machineDataService = machineDataService;
    }

    @GetMapping("/machine_data")
    public Map<String, Integer> getMachineData(@RequestParam(name = "fromDate", required = false) LocalDate fromDate,
                                               @RequestParam(name = "fromTime", required = false) LocalTime fromTime,
                                               @RequestParam(name = "toDate", required = false) LocalDate toDate,
                                               @RequestParam(name = "toTime", required = false) LocalTime toTime) throws CsvValidationException, IOException {
        log.debug("Inside method getMachineData");
        log.info("fromDate: {} || fromTime: {} || toDate: {} || toTime: {}", fromDate, fromTime, toDate, toTime);
        LocalDateTime fromLocalDateTime;
        if (fromDate != null && fromTime != null) {
            fromLocalDateTime = fromDate.atTime(fromTime);
        } else {
            fromLocalDateTime = LocalDateTime.of(2021, 1, 27, 4, 0, 0);
        }

        LocalDateTime toLocalDateTime;
        if (toDate != null && toTime != null) {
            toLocalDateTime = toDate.atTime(toTime);
        } else {
            toLocalDateTime = LocalDateTime.of(2021, 1, 28, 4, 0, 0);
        }

        Map<String, Integer> powerDistributionInTimeRange =
                machineDataService.getPowerDistributionInTimeRange(fromLocalDateTime, toLocalDateTime);

        return powerDistributionInTimeRange;
    }

    @GetMapping("/machine_time_series_data")
    public Map<LocalDateTime, Double> getMachineTimeSeriesData(@RequestParam(name = "fromDate", required = false) LocalDate fromDate,
                                                               @RequestParam(name = "fromTime", required = false) LocalTime fromTime) throws CsvValidationException, IOException {
        log.debug("Inside method getMachineTimeSeriesData");
        log.info("fromDate: {} || fromTime: {}", fromDate, fromTime);
        LocalDateTime fromLocalDateTime;
        if (fromDate != null && fromTime != null) {
            fromLocalDateTime = fromDate.atTime(fromTime);
        } else {
            fromLocalDateTime = LocalDateTime.of(2021, 1, 27, 4, 0, 0);
        }

        return machineDataService.getPowerDistributionTimeSeries(fromLocalDateTime);
    }

}
