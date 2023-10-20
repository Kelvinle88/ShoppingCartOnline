package osc.publisher;

import osc.dto.OrderDto;

public interface PaymentPublisher {
    void updateOrderPayment (OrderDto orderDto);
}
