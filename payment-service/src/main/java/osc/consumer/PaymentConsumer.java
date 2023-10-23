package osc.consumer;

import com.stripe.exception.StripeException;
import osc.events.OrderEvent;

public interface PaymentConsumer {
    //void receiveMessageFromOrder(OrderDto orderDto) throws StripeException;
    public void processOrderEvent(OrderEvent orderEvent) throws StripeException;
}
