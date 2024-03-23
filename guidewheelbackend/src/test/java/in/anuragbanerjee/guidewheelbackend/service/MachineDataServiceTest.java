package in.anuragbanerjee.guidewheelbackend.service;

import com.opencsv.exceptions.CsvValidationException;
import in.anuragbanerjee.guidewheelbackend.data.Row;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MachineDataServiceTest {

    @Spy
    @InjectMocks
    private MachineDataService machineDataService;

    @Test
    public void testGetPowerDistributionTimeSeries() throws IOException, CsvValidationException {
        // given
        ZoneId zoneId = ZoneId.of("UTC");

        Row row1 = new Row();
        row1.setDeviceid("demo_t_afm16");
        row1.setFromts(1611722280000L);
        row1.setTots(1611722280000L);
        row1.setMetrics("{\"F\": {\"avgvalue\": 49.98786666666665, \"maxvalue\": 50.053, \"minvalue\": 49.917}, \"I1\": {\"avgvalue\": 262.2780246666667, \"maxvalue\": 488.34536, \"minvalue\": 84.29016}, \"I2\": {\"avgvalue\": 234.07998733333338, \"maxvalue\": 429.77180000000004, \"minvalue\": 117.3104}, \"I3\": {\"avgvalue\": 216.60306133333336, \"maxvalue\": 337.83888, \"minvalue\": 93.5406}, \"P1\": {\"avgvalue\": 57.875538, \"maxvalue\": 115.1752, \"minvalue\": 13.53968}, \"P2\": {\"avgvalue\": 51.012230666666674, \"maxvalue\": 102.87896, \"minvalue\": 19.96412}, \"P3\": {\"avgvalue\": 46.097397999999984, \"maxvalue\": 78.88308, \"minvalue\": 12.1204}, \"S1\": {\"avgvalue\": 62.67042266666668, \"maxvalue\": 116.48143999999999, \"minvalue\": 20.221600000000002}, \"S2\": {\"avgvalue\": 56.877855333333336, \"maxvalue\": 104.46152000000001, \"minvalue\": 28.47352}, \"S3\": {\"avgvalue\": 51.91550399999999, \"maxvalue\": 80.86128, \"minvalue\": 22.482400000000002}, \"PF1\": {\"avgvalue\": 0.8875761714872195, \"maxvalue\": 0.993594306049822, \"minvalue\": 0.6659613059250302}, \"PF2\": {\"avgvalue\": 0.8711828868672513, \"maxvalue\": 0.9848503066009379, \"minvalue\": 0.6583143507972665}, \"PF3\": {\"avgvalue\": 0.8635852144308974, \"maxvalue\": 0.9755358807082946, \"minvalue\": 0.5391061452513967}, \"Iavg\": {\"avgvalue\": 262.2780246666667, \"maxvalue\": 488.34536, \"minvalue\": 84.29016}, \"Psum\": {\"avgvalue\": 154.9851666666667, \"maxvalue\": 278.07212000000004, \"minvalue\": 77.75896}, \"Ssum\": {\"avgvalue\": 171.46378199999998, \"maxvalue\": 286.63804, \"minvalue\": 96.9946}, \"Vll1\": {\"avgvalue\": 417.0639, \"maxvalue\": 418.311, \"minvalue\": 415.93}, \"Vll2\": {\"avgvalue\": 418.7803833333331, \"maxvalue\": 420.159, \"minvalue\": 417.726}, \"Vll3\": {\"avgvalue\": 414.49965000000003, \"maxvalue\": 415.821, \"minvalue\": 412.989}, \"Vln1\": {\"avgvalue\": 239.1234, \"maxvalue\": 240.744, \"minvalue\": 238.086}, \"Vln2\": {\"avgvalue\": 243.02486666666664, \"maxvalue\": 244.231, \"minvalue\": 242.053}, \"Vln3\": {\"avgvalue\": 239.74745000000004, \"maxvalue\": 241.211, \"minvalue\": 238.784}, \"engy\": {\"avgvalue\": 990829.1465321275, \"maxvalue\": 990829.1465321275, \"minvalue\": 990829.1465321275}, \"PFavg\": {\"avgvalue\": 0.8915333333333332, \"maxvalue\": 0.972, \"minvalue\": 0.741}, \"Vllavg\": {\"avgvalue\": 416.78131111111105, \"maxvalue\": 417.613, \"minvalue\": 415.811}, \"Vlnavg\": {\"avgvalue\": 240.6319055555556, \"maxvalue\": 241.11533333333335, \"minvalue\": 240.07433333333336}, \"apparentEngy\": {\"avgvalue\": 1098129.4305833972, \"maxvalue\": 1098129.4305833972, \"minvalue\": 1098129.4305833972}}");
        LocalDateTime fromLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row1.getFromts()),
                zoneId
        );
        row1.setFromLocalDateTime(fromLocalDateTime);
        LocalDateTime toLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row1.getTots()),
                zoneId
        );
        row1.setToLocalDateTime(toLocalDateTime);

        Row row2 = new Row();
        row2.setDeviceid("demo_t_afm16");
        row2.setFromts(1611722340000L);
        row2.setTots(1611722400000L);
        row2.setMetrics("{\"F\": {\"avgvalue\": 50.075766666666645, \"maxvalue\": 50.132, \"minvalue\": 49.948}, \"I1\": {\"avgvalue\": 260.9593293333334, \"maxvalue\": 473.34872, \"minvalue\": 75.49188000000001}, \"I2\": {\"avgvalue\": 228.41521800000007, \"maxvalue\": 419.46632, \"minvalue\": 95.28016000000001}, \"I3\": {\"avgvalue\": 214.19687933333333, \"maxvalue\": 332.63276, \"minvalue\": 97.97428000000001}, \"P1\": {\"avgvalue\": 56.70023599999998, \"maxvalue\": 111.34440000000001, \"minvalue\": 11.147}, \"P2\": {\"avgvalue\": 49.63136333333334, \"maxvalue\": 99.99016, \"minvalue\": 14.682640000000001}, \"P3\": {\"avgvalue\": 46.171502, \"maxvalue\": 77.4952, \"minvalue\": 14.58844}, \"S1\": {\"avgvalue\": 62.52545933333333, \"maxvalue\": 113.17815999999999, \"minvalue\": 18.11152}, \"S2\": {\"avgvalue\": 55.61651733333334, \"maxvalue\": 101.94952000000002, \"minvalue\": 23.22344}, \"S3\": {\"avgvalue\": 51.52572533333334, \"maxvalue\": 79.8816, \"minvalue\": 23.63164}, \"PF1\": {\"avgvalue\": 0.8667393355682778, \"maxvalue\": 0.9922998599974545, \"minvalue\": 0.6154646324549237}, \"PF2\": {\"avgvalue\": 0.862669164314272, \"maxvalue\": 0.9840807610172781, \"minvalue\": 0.6322336398053001}, \"PF3\": {\"avgvalue\": 0.8822669785304812, \"maxvalue\": 0.970125786163522, \"minvalue\": 0.6173266011161308}, \"Iavg\": {\"avgvalue\": 260.9593293333334, \"maxvalue\": 473.34872, \"minvalue\": 75.49188000000001}, \"Psum\": {\"avgvalue\": 152.50310133333332, \"maxvalue\": 248.83872000000002, \"minvalue\": 77.21888}, \"Ssum\": {\"avgvalue\": 169.66770199999993, \"maxvalue\": 262.82428, \"minvalue\": 96.70572}, \"Vll1\": {\"avgvalue\": 418.1734, \"maxvalue\": 419.373, \"minvalue\": 417.001}, \"Vll2\": {\"avgvalue\": 419.70376666666675, \"maxvalue\": 420.794, \"minvalue\": 418.739}, \"Vll3\": {\"avgvalue\": 415.77126666666663, \"maxvalue\": 417.61, \"minvalue\": 414.573}, \"Vln1\": {\"avgvalue\": 239.73003333333332, \"maxvalue\": 240.682, \"minvalue\": 238.528}, \"Vln2\": {\"avgvalue\": 243.49078333333338, \"maxvalue\": 244.433, \"minvalue\": 242.68}, \"Vln3\": {\"avgvalue\": 240.58126666666672, \"maxvalue\": 241.693, \"minvalue\": 239.609}, \"engy\": {\"avgvalue\": 990833.2058687083, \"maxvalue\": 990833.2058687083, \"minvalue\": 990833.2058687083}, \"PFavg\": {\"avgvalue\": 0.8857666666666667, \"maxvalue\": 0.97, \"minvalue\": 0.711}, \"Vllavg\": {\"avgvalue\": 417.8828111111113, \"maxvalue\": 418.8793333333333, \"minvalue\": 417.0503333333333}, \"Vlnavg\": {\"avgvalue\": 241.26736111111111, \"maxvalue\": 241.843, \"minvalue\": 240.78533333333334}, \"apparentEngy\": {\"avgvalue\": 1098133.9326536655, \"maxvalue\": 1098133.9326536655, \"minvalue\": 1098133.9326536655}}");
        fromLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row2.getFromts()),
                zoneId
        );
        row2.setFromLocalDateTime(fromLocalDateTime);
        toLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row2.getTots()),
                zoneId
        );
        row2.setToLocalDateTime(toLocalDateTime);

        // There need to be at least 10 rows for calculation of operating load
        List<Row> mockRowList = List.of(row1, row2, row1, row2, row1, row1, row2, row1, row2, row1);
        when(machineDataService.readCSVData()).thenReturn(mockRowList);

        // when
        Map<LocalDateTime, Double> result = machineDataService.getPowerDistributionTimeSeries(row1.getFromLocalDateTime());

        // then
        assertEquals(720, result.size(), "Map size should be 720 for 12 hrs of data");
    }

    @Test
    public void testGetPowerDistributionInTimeRange() throws IOException, CsvValidationException {
        // given
        ZoneId zoneId = ZoneId.of("UTC");

        Row row1 = new Row();
        row1.setDeviceid("demo_t_afm16");
        row1.setFromts(1611722280000L);
        row1.setTots(1611722280000L);
        row1.setMetrics("{\"F\": {\"avgvalue\": 49.98786666666665, \"maxvalue\": 50.053, \"minvalue\": 49.917}, \"I1\": {\"avgvalue\": 262.2780246666667, \"maxvalue\": 488.34536, \"minvalue\": 84.29016}, \"I2\": {\"avgvalue\": 234.07998733333338, \"maxvalue\": 429.77180000000004, \"minvalue\": 117.3104}, \"I3\": {\"avgvalue\": 216.60306133333336, \"maxvalue\": 337.83888, \"minvalue\": 93.5406}, \"P1\": {\"avgvalue\": 57.875538, \"maxvalue\": 115.1752, \"minvalue\": 13.53968}, \"P2\": {\"avgvalue\": 51.012230666666674, \"maxvalue\": 102.87896, \"minvalue\": 19.96412}, \"P3\": {\"avgvalue\": 46.097397999999984, \"maxvalue\": 78.88308, \"minvalue\": 12.1204}, \"S1\": {\"avgvalue\": 62.67042266666668, \"maxvalue\": 116.48143999999999, \"minvalue\": 20.221600000000002}, \"S2\": {\"avgvalue\": 56.877855333333336, \"maxvalue\": 104.46152000000001, \"minvalue\": 28.47352}, \"S3\": {\"avgvalue\": 51.91550399999999, \"maxvalue\": 80.86128, \"minvalue\": 22.482400000000002}, \"PF1\": {\"avgvalue\": 0.8875761714872195, \"maxvalue\": 0.993594306049822, \"minvalue\": 0.6659613059250302}, \"PF2\": {\"avgvalue\": 0.8711828868672513, \"maxvalue\": 0.9848503066009379, \"minvalue\": 0.6583143507972665}, \"PF3\": {\"avgvalue\": 0.8635852144308974, \"maxvalue\": 0.9755358807082946, \"minvalue\": 0.5391061452513967}, \"Iavg\": {\"avgvalue\": 262.2780246666667, \"maxvalue\": 488.34536, \"minvalue\": 84.29016}, \"Psum\": {\"avgvalue\": 154.9851666666667, \"maxvalue\": 278.07212000000004, \"minvalue\": 77.75896}, \"Ssum\": {\"avgvalue\": 171.46378199999998, \"maxvalue\": 286.63804, \"minvalue\": 96.9946}, \"Vll1\": {\"avgvalue\": 417.0639, \"maxvalue\": 418.311, \"minvalue\": 415.93}, \"Vll2\": {\"avgvalue\": 418.7803833333331, \"maxvalue\": 420.159, \"minvalue\": 417.726}, \"Vll3\": {\"avgvalue\": 414.49965000000003, \"maxvalue\": 415.821, \"minvalue\": 412.989}, \"Vln1\": {\"avgvalue\": 239.1234, \"maxvalue\": 240.744, \"minvalue\": 238.086}, \"Vln2\": {\"avgvalue\": 243.02486666666664, \"maxvalue\": 244.231, \"minvalue\": 242.053}, \"Vln3\": {\"avgvalue\": 239.74745000000004, \"maxvalue\": 241.211, \"minvalue\": 238.784}, \"engy\": {\"avgvalue\": 990829.1465321275, \"maxvalue\": 990829.1465321275, \"minvalue\": 990829.1465321275}, \"PFavg\": {\"avgvalue\": 0.8915333333333332, \"maxvalue\": 0.972, \"minvalue\": 0.741}, \"Vllavg\": {\"avgvalue\": 416.78131111111105, \"maxvalue\": 417.613, \"minvalue\": 415.811}, \"Vlnavg\": {\"avgvalue\": 240.6319055555556, \"maxvalue\": 241.11533333333335, \"minvalue\": 240.07433333333336}, \"apparentEngy\": {\"avgvalue\": 1098129.4305833972, \"maxvalue\": 1098129.4305833972, \"minvalue\": 1098129.4305833972}}");
        LocalDateTime fromLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row1.getFromts()),
                zoneId
        );
        row1.setFromLocalDateTime(fromLocalDateTime);
        LocalDateTime toLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row1.getTots()),
                zoneId
        );
        row1.setToLocalDateTime(toLocalDateTime);

        Row row2 = new Row();
        row2.setDeviceid("demo_t_afm16");
        row2.setFromts(1611722340000L);
        row2.setTots(1611722400000L);
        row2.setMetrics("{\"F\": {\"avgvalue\": 50.075766666666645, \"maxvalue\": 50.132, \"minvalue\": 49.948}, \"I1\": {\"avgvalue\": 260.9593293333334, \"maxvalue\": 473.34872, \"minvalue\": 75.49188000000001}, \"I2\": {\"avgvalue\": 228.41521800000007, \"maxvalue\": 419.46632, \"minvalue\": 95.28016000000001}, \"I3\": {\"avgvalue\": 214.19687933333333, \"maxvalue\": 332.63276, \"minvalue\": 97.97428000000001}, \"P1\": {\"avgvalue\": 56.70023599999998, \"maxvalue\": 111.34440000000001, \"minvalue\": 11.147}, \"P2\": {\"avgvalue\": 49.63136333333334, \"maxvalue\": 99.99016, \"minvalue\": 14.682640000000001}, \"P3\": {\"avgvalue\": 46.171502, \"maxvalue\": 77.4952, \"minvalue\": 14.58844}, \"S1\": {\"avgvalue\": 62.52545933333333, \"maxvalue\": 113.17815999999999, \"minvalue\": 18.11152}, \"S2\": {\"avgvalue\": 55.61651733333334, \"maxvalue\": 101.94952000000002, \"minvalue\": 23.22344}, \"S3\": {\"avgvalue\": 51.52572533333334, \"maxvalue\": 79.8816, \"minvalue\": 23.63164}, \"PF1\": {\"avgvalue\": 0.8667393355682778, \"maxvalue\": 0.9922998599974545, \"minvalue\": 0.6154646324549237}, \"PF2\": {\"avgvalue\": 0.862669164314272, \"maxvalue\": 0.9840807610172781, \"minvalue\": 0.6322336398053001}, \"PF3\": {\"avgvalue\": 0.8822669785304812, \"maxvalue\": 0.970125786163522, \"minvalue\": 0.6173266011161308}, \"Iavg\": {\"avgvalue\": 260.9593293333334, \"maxvalue\": 473.34872, \"minvalue\": 75.49188000000001}, \"Psum\": {\"avgvalue\": 152.50310133333332, \"maxvalue\": 248.83872000000002, \"minvalue\": 77.21888}, \"Ssum\": {\"avgvalue\": 169.66770199999993, \"maxvalue\": 262.82428, \"minvalue\": 96.70572}, \"Vll1\": {\"avgvalue\": 418.1734, \"maxvalue\": 419.373, \"minvalue\": 417.001}, \"Vll2\": {\"avgvalue\": 419.70376666666675, \"maxvalue\": 420.794, \"minvalue\": 418.739}, \"Vll3\": {\"avgvalue\": 415.77126666666663, \"maxvalue\": 417.61, \"minvalue\": 414.573}, \"Vln1\": {\"avgvalue\": 239.73003333333332, \"maxvalue\": 240.682, \"minvalue\": 238.528}, \"Vln2\": {\"avgvalue\": 243.49078333333338, \"maxvalue\": 244.433, \"minvalue\": 242.68}, \"Vln3\": {\"avgvalue\": 240.58126666666672, \"maxvalue\": 241.693, \"minvalue\": 239.609}, \"engy\": {\"avgvalue\": 990833.2058687083, \"maxvalue\": 990833.2058687083, \"minvalue\": 990833.2058687083}, \"PFavg\": {\"avgvalue\": 0.8857666666666667, \"maxvalue\": 0.97, \"minvalue\": 0.711}, \"Vllavg\": {\"avgvalue\": 417.8828111111113, \"maxvalue\": 418.8793333333333, \"minvalue\": 417.0503333333333}, \"Vlnavg\": {\"avgvalue\": 241.26736111111111, \"maxvalue\": 241.843, \"minvalue\": 240.78533333333334}, \"apparentEngy\": {\"avgvalue\": 1098133.9326536655, \"maxvalue\": 1098133.9326536655, \"minvalue\": 1098133.9326536655}}");
        fromLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row2.getFromts()),
                zoneId
        );
        row2.setFromLocalDateTime(fromLocalDateTime);
        toLocalDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(row2.getTots()),
                zoneId
        );
        row2.setToLocalDateTime(toLocalDateTime);

        // There need to be at least 10 rows for calculation of operating load
        List<Row> mockRowList = List.of(row1, row2, row1, row2, row1, row1, row2, row1, row2, row1);

        // when
        when(machineDataService.readCSVData()).thenReturn(mockRowList);
        Map<String, Integer> result = machineDataService.getPowerDistributionInTimeRange(LocalDateTime.now(), LocalDateTime.now());

        // then
        assertEquals(4, result.size(), "There should be 4 entries in the Map for 4 different type of loads");
    }
}
