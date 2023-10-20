package osc.service;

import osc.constant.OrderStatus;
import osc.dto.ProductDto;
import osc.entity.Order;

import java.util.List;

public interface OrderService {
    public Order saveOrder(Order order);
    public List <ProductDto> getProductsFromOrder(Order order);

    public void updateOrderStatus (Long id,OrderStatus status);
    public Order getOrder(Long id);
}
