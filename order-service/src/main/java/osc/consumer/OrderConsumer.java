package osc.consumer;

import osc.events.PaymentEvent;

public interface OrderConsumer {
    //void receiveMessageFromPayment(OrderDto orderDto);
    public void processPaymentEvent(PaymentEvent paymentEvent);
}
