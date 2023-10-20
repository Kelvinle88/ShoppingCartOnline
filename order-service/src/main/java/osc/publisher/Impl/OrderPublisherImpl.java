package osc.publisher.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import osc.dto.OrderDto;
import osc.dto.ProductDto;
import osc.publisher.OrderPublisher;

import java.util.List;

@Service
public class OrderPublisherImpl implements OrderPublisher {
    @Autowired
    private KafkaTemplate <String, Object> kafkaTemplate;
    @Override
    public void placeOrderMessage (OrderDto orderDto) {
        kafkaTemplate.send("update-order-topic", orderDto);
    }
    @Override
    public void updateProductShipOut (List <ProductDto> productDtos) {
        kafkaTemplate.send ("update-product-topic",productDtos);
    }
}
