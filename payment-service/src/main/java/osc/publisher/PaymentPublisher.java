package osc.publisher;

import osc.dto.OrderDto;

public interface PaymentPublisher {
    public void paymentConfirm (OrderDto orderDto);
    public void paymentCanceled (OrderDto orderDto);
}
