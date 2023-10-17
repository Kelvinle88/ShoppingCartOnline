package osc.orderservice.service;

import osc.orderservice.dto.ProductDto;
import osc.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    public Order saveOrder(Order order);
    public List <ProductDto> getProductsFromOrder(Order order);
}
