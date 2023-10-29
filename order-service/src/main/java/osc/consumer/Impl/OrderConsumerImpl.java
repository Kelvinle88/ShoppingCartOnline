package osc.consumer.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import osc.constant.PaymentStatus;
import osc.consumer.OrderConsumer;
import osc.dto.OrderDto;
import osc.events.PaymentEvent;
import osc.service.OrderService;

@Service
public class OrderConsumerImpl implements OrderConsumer {
    @Autowired
    private OrderService orderService;


    @KafkaListener(
            topics = "payment-order-events",
            containerFactory = "orderKafkaListenerContainerFactory",
            groupId = "pm")
//    @Override
//    public void receiveMessageFromPayment (OrderDto orderDto) {
//        orderService.updateOrderStatus(orderDto.getId (), orderDto.getOrderStatus (),orderDto.getPaymentStatus ());
//        }
    @Override
    public void processPaymentEvent(PaymentEvent paymentEvent) {
        if (paymentEvent.getPaymentStatus ().equals (PaymentStatus.CONFIRMED)) {
            OrderDto orderDto = paymentEvent.getOrderDto ();
            orderService.updateOrderStatus(orderDto.getId (), orderDto.getOrderStatus (),orderDto.getPaymentStatus ());
        } else if (paymentEvent.getPaymentStatus ().equals (PaymentStatus.REJECTED)) {
            OrderDto orderDto = paymentEvent.getOrderDto ();
            orderService.rejectOrder (orderDto);
        }
    }
}
