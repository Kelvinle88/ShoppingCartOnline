package osc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import osc.constant.OrderStatus;
import osc.constant.PaymentStatus;
import osc.dto.OrderDto;
import osc.dto.ProductDto;
import osc.entity.Item;
import osc.entity.Order;
import osc.mapper.OrderMapper;
import osc.publisher.OrderPublisher;
import osc.repository.OrderRepository;
import osc.security.AuthHelper;
import osc.service.OrderService;
import osc.utilities.OrderUtilities;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderPublisher orderPublisher;

    @Autowired
    private AuthHelper authHelper;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List <ProductDto> getProductsFromOrder(Order order) {
        Map <Long, Integer> map = new HashMap <> ();
        List<ProductDto> productDtos = new ArrayList <> ();
        order.getItems ()
                .stream ()
                .filter(Objects::nonNull)
                .forEach (Item ->map.merge (Item.getProduct ().getProductId (),//Item.getProduct().getId(),
                        Item.getQuantity(),
                        Integer::sum
                        ));
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();
            ProductDto productDto = new ProductDto();
            productDto.setProductId(productId);
            productDto.setQuantity(quantity);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    @Async
    public OrderDto updateOrderStatus (Long id,OrderStatus orderStatus,PaymentStatus paymentStatus) {
        Order order = orderRepository.findById (id).orElse (null);
        order.setOrderStatus (orderStatus);
        order.setPaymentStatus (paymentStatus);
        orderRepository.save (order);
        if(orderStatus.equals (OrderStatus.CONFIRMED) && paymentStatus.equals (PaymentStatus.CONFIRMED)){
            orderPublisher.createOrderToProduct (getProductsFromOrder (order));
            orderPublisher.createOrderToEmail (orderMapper.toDto (order));
        }
        return orderMapper.toDto (order);
    }
    @Override
    public Order createOrder(List<Item> cart, String userId) {
        Order order = new Order();
        order.setItems(cart);
        order.setUserId (userId);
        order.setTotal(OrderUtilities.countTotalPrice(cart));
        order.setTotalQuantity (OrderUtilities.countTotalQuantity (cart));
        order.setOrderedDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setPaymentStatus (PaymentStatus.PENDING);
        return order;
    }

    @Override
    @Async
    public CompletableFuture<OrderDto> requestPaymentOrder (OrderDto orderDto) {
        orderPublisher.createOrderToPayment (orderDto);
        return CompletableFuture.completedFuture(orderDto);
    }
    public Order findOrderById(Long id){
        return orderRepository.findById (id).orElse (null);
    }

    @Override
    public ResponseEntity <OrderDto> cancelOrder (OrderDto orderDto) {
        Order order = orderRepository.findById (orderDto.getId ()).orElse (null);
        order.setOrderStatus (OrderStatus.ROLLBACK);
        order.setPaymentStatus (PaymentStatus.ROLLBACK);
        orderRepository.save (order);
        orderPublisher.cancelOrderToPayment (orderDto);
        orderPublisher.cancelOrderToProduct (getProductsFromOrder (order));
        orderPublisher.cancelOrderToEmail (orderDto);

        return new ResponseEntity<> (orderMapper.toDto (order),HttpStatus.NO_CONTENT);
    }

    @Override
    public List<OrderDto> getOrderByUserId () {
        String userId = authHelper.getUserId ();
        List<Order> orders = orderRepository.findAllByUserId (userId);
        return orderMapper.toDtos (orders);
    }

    @Override
    public List <OrderDto> getAllOrders () {
        return orderRepository.findAll ().stream()
                .map(p -> orderMapper.toDto ((p)))
                .toList();
    }
}
