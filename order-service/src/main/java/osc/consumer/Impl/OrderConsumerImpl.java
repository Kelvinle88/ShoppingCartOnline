package osc.consumer.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import osc.consumer.OrderConsumer;
import osc.dto.OrderDto;
import osc.service.OrderService;

@Service
public class OrderConsumerImpl implements OrderConsumer {
    @Autowired
    private OrderService orderService;


    @KafkaListener(
            topics = "update-payment-topic",
            containerFactory = "orderKafkaListenerContainerFactory",
            groupId = "pm")
    @Override
    public void receiveMessageFromPayment (OrderDto orderDto) {
        orderService.updateOrderStatus(orderDto.getId (), orderDto.getOrderStatus (),orderDto.getPaymentStatus ());
        }
}
