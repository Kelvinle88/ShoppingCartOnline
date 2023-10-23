package osc.service;

import com.stripe.exception.StripeException;
import osc.dto.OrderDto;

public interface PaymentService {
    public String checkOut(OrderDto orderDTO) throws StripeException;

    public String rollBack (OrderDto orderDto) throws StripeException;
}
