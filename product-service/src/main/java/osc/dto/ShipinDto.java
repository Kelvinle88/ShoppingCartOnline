package osc.dto;

import lombok.*;
import osc.enums.ShipEventType;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipinDto {
    private Long shipInId;
    private LocalDateTime shipmentDate;
    private int quantity;
    private ShipEventType shipEventType;
}
