package osc.events;

import lombok.*;
import osc.dto.OrderDto;
import osc.enums.EventType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OrderEvent {
    private EventType eventType;
    private OrderDto orderDto;
}
