package osc.service;

import org.springframework.http.ResponseEntity;
import osc.constant.OrderStatus;
import osc.constant.PaymentStatus;
import osc.dto.OrderDto;
import osc.dto.ProductDto;
import osc.entity.Item;
import osc.entity.Order;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    public Order saveOrder(Order order);
    public List <ProductDto> getProductsFromOrder(Order order);

    public OrderDto updateOrderStatus (Long id,OrderStatus orderStatus,PaymentStatus paymentStatus);
    public Order createOrder(List<Item> cart,String userId);
    public CompletableFuture<OrderDto> requestPaymentOrder(OrderDto orderDto);
    public Order findOrderById(Long id);

    ResponseEntity<OrderDto> cancelOrder (OrderDto orderDto);

    List<OrderDto> getOrderByUserId ();
}
