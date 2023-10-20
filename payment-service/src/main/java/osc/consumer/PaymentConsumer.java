package osc.consumer;

import com.stripe.exception.StripeException;
import osc.dto.OrderDto;

public interface PaymentConsumer {
    void receiveMessageFromOrder(OrderDto orderDto) throws StripeException;
}
