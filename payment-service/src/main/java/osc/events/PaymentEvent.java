package osc.events;

import lombok.*;
import osc.dto.OrderDto;
import osc.enums.PaymentStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PaymentEvent {
    private PaymentStatus paymentStatus;
    private OrderDto orderDto;
}
