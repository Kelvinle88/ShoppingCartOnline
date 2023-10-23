package osc.publisher;

import osc.dto.OrderDto;
import osc.dto.ProductDto;

import java.util.List;

public interface OrderPublisher {
    void createOrderToPayment(OrderDto orderDto);
    void cancelOrderToPayment(OrderDto orderDto);

//    void updateProductShipOut(List <ProductDto> productDtos);
    void sendEmailOrderDetail(OrderDto orderDto);
    public void creatOrderToProduct(List<ProductDto> productDtos);
    public void cancelOrderToProduct(List<ProductDto> productDtos);
}
