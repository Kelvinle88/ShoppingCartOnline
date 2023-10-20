package osc.consumer.Impl;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import osc.consumer.PaymentConsumer;
import osc.dto.OrderDto;
import osc.service.PaymentService;

@Service
public class PaymentConsumerImpl implements PaymentConsumer {
    @Autowired
    private PaymentService paymentService;

    @KafkaListener(
            topics = "update-order-topic",
            containerFactory = "paymentKafkaListenerContainerFactory",
            groupId = "pm")
    @Override
    public void receiveMessageFromOrder (OrderDto orderDto) throws StripeException {
        paymentService.CheckOut (orderDto);
    }
}
