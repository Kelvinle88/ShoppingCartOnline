package osc.consumer;

import osc.events.OrderEvent;

import javax.mail.MessagingException;

public interface EmailConsumer {
    //void receiveMessageFromOrder(OrderDto orderDto) throws MessagingException;
    void processOrderEvent(OrderEvent orderEvent) throws MessagingException;
}
