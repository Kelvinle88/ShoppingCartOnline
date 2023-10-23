package osc.publisher.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import osc.constant.EventType;
import osc.dto.OrderDto;
import osc.dto.ProductDto;
import osc.events.OrderEvent;
import osc.publisher.OrderPublisher;

import java.util.List;

@Service
public class OrderPublisherImpl implements OrderPublisher {
    @Autowired
    private KafkaTemplate <String, Object> kafkaTemplate;

//    @Autowired
//    private KafkaTemplate <String, OrderEvent> kafkaTemplate;
    @Override
    public void createOrderToPayment (OrderDto orderDto) {
        OrderEvent orderEvent = new OrderEvent(EventType.ORDER_CREATED, orderDto);
        kafkaTemplate.send("order-payment-events", orderEvent);
    }
    @Override
    public void cancelOrderToPayment (OrderDto orderDto) {
        OrderEvent orderEvent = new OrderEvent(EventType.ORDER_CANCELED, orderDto);
        kafkaTemplate.send("order-payment-events", orderEvent);
    }
//    @Override
//    public void updateProductShipOut (List <ProductDto> productDtos) {
//        kafkaTemplate.send ("update-product-topic",productDtos);
//    }

    @Override
    public void sendEmailOrderDetail (OrderDto orderDto) {
        kafkaTemplate.send("email-order-topic", orderDto);
    }

    @Override
    public void creatOrderToProduct(List<ProductDto> productDtos) {
        OrderEvent orderEvent = new OrderEvent(EventType.ORDER_CREATED, productDtos);
        kafkaTemplate.send("order-product-events", orderEvent);
    }

    @Override
    public void cancelOrderToProduct(List<ProductDto> productDtos) {
        OrderEvent orderEvent = new OrderEvent(EventType.ORDER_CANCELED, productDtos);
        kafkaTemplate.send("order-product-events", orderEvent);
    }
}
