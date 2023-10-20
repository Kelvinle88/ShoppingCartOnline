package osc.service;

import com.stripe.exception.StripeException;
import osc.dto.OrderDto;

public interface PaymentService {
    public void CheckOut(OrderDto orderDTO) throws StripeException;
}
