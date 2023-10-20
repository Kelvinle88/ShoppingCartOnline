package osc.publisher;

import osc.dto.OrderDto;
import osc.dto.ProductDto;

import java.util.List;

public interface OrderPublisher {
    void placeOrderMessage(OrderDto orderDto);

    void updateProductShipOut(List <ProductDto> productDtos);
}
