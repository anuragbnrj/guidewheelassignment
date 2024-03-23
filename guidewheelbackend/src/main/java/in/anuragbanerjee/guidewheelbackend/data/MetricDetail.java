package in.anuragbanerjee.guidewheelbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricDetail {
    private Double avgvalue;
    private Double maxvalue;
    private Double minvalue;
}
