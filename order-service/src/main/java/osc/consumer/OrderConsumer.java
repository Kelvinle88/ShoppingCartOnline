package osc.consumer;

import osc.dto.OrderDto;

public interface OrderConsumer {
    void receiveMessageFromPayment(OrderDto orderDto);
}
