package osc.consumer;

import osc.dto.OrderDto;

import javax.mail.MessagingException;

public interface EmailConsumer {
    void receiveMessageFromOrder(OrderDto orderDto) throws MessagingException;
    //public void processOrderEvent(OrderEvent orderEvent) throws MessagingException;
}
