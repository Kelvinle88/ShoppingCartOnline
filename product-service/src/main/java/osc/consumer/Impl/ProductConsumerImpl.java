package osc.consumer.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import osc.consumer.ProductConsumer;
import osc.dto.ProductDto;
import osc.mapper.ProductMapper;
import osc.service.ProductService;

import java.util.List;

@Service
public class ProductConsumerImpl implements ProductConsumer {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @KafkaListener(
            topics = "update-product-topic",
            containerFactory = "productKafkaListenerContainerFactory",
            groupId = "pm")
    @Override
    public void receiveMessageFromOrder (List <ProductDto> productDtos) {
        productService.updateProductShipOut(productDtos);

    }
}
