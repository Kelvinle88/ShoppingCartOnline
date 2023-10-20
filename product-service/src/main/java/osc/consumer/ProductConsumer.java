package osc.consumer;

import osc.dto.ProductDto;

import java.util.List;

public interface ProductConsumer {
    void receiveMessageFromOrder(List <ProductDto> productDtos);
}
