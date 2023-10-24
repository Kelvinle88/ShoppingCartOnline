package osc.publisher;

import osc.dto.OrderDto;
import osc.dto.ProductDto;

import java.util.List;

public interface OrderPublisher {
    void createOrderToPayment(OrderDto orderDto);
    void cancelOrderToPayment(OrderDto orderDto);
    public void createOrderToEmail(OrderDto orderDto);
    public void cancelOrderToEmail (OrderDto orderDto);
    public void createOrderToProduct(List<ProductDto> productDtos);
    public void cancelOrderToProduct(List<ProductDto> productDtos);
}
