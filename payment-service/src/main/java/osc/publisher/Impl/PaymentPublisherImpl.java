package osc.publisher.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import osc.dto.OrderDto;
import osc.publisher.PaymentPublisher;

@Service
public class PaymentPublisherImpl implements PaymentPublisher {
    @Autowired
    private KafkaTemplate <String, Object> kafkaTemplate;
    @Override
    public void updateOrderPayment (OrderDto orderDto) {
        kafkaTemplate.send("update-payment-topic", orderDto);
    }

}
