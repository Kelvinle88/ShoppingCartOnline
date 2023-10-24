package osc.publisher.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import osc.dto.OrderDto;
import osc.enums.PaymentStatus;
import osc.events.PaymentEvent;
import osc.publisher.PaymentPublisher;

@Service
public class PaymentPublisherImpl implements PaymentPublisher {
    @Autowired
    private KafkaTemplate <String, Object> kafkaTemplate;
    @Override
    public void paymentConfirm (OrderDto orderDto) {
        PaymentEvent paymentEvent = new PaymentEvent (PaymentStatus.CONFIRMED,orderDto);
        kafkaTemplate.send("payment-order-events", paymentEvent);
    }

    @Override
    public void paymentCanceled (OrderDto orderDto) {
        PaymentEvent paymentEvent = new PaymentEvent (PaymentStatus.ROLLBACK,orderDto);
        kafkaTemplate.send("payment-events", paymentEvent);
    }

}
