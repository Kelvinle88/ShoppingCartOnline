package osc.consumer.Impl;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import osc.consumer.PaymentConsumer;
import osc.dto.OrderDto;
import osc.enums.EventType;
import osc.events.OrderEvent;
import osc.service.PaymentService;

@Service
public class PaymentConsumerImpl implements PaymentConsumer {
    @Autowired
    private PaymentService paymentService;

    @KafkaListener(
            topics = "order-payment-events",
            containerFactory = "paymentKafkaListenerContainerFactory",
            groupId = "pm")
//    @Override
//    public void receiveMessageFromOrder (OrderDto orderDto) throws StripeException {
//        paymentService.checkOut (orderDto);
//    }
    @Override
    public void processOrderEvent(OrderEvent orderEvent) throws StripeException {
        if (orderEvent.getEventType ().equals (EventType.ORDER_CREATED)) {
            OrderDto orderDto = orderEvent.getOrderDto ();
            paymentService.checkOut (orderDto);
        } else if (orderEvent.getEventType ().equals (EventType.ORDER_CANCELED)) {
            OrderDto orderDto = orderEvent.getOrderDto ();
            paymentService.rollBack (orderDto);
        }
    }
}
