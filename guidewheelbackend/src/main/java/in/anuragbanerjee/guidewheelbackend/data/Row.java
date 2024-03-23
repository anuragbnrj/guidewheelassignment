package in.anuragbanerjee.guidewheelbackend.data;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Row {
    private String deviceid;
    private Long fromts;
    private Long tots;
    private String metrics;
    private LocalDateTime fromLocalDateTime;
    private LocalDateTime toLocalDateTime;
}
