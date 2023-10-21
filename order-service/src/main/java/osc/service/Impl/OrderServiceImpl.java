package osc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
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
                .forEach (Item ->map.merge (Item.getProduct().getId(),
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
    public OrderDto updateOrderStatus (Long id,OrderStatus orderStatus,PaymentStatus paymentStatus) {
        Order order = orderRepository.findById (id).orElse (null);
        order.setOrderStatus (orderStatus);
        order.setPaymentStatus (paymentStatus);
        orderRepository.save (order);
        if(orderStatus.equals (OrderStatus.CONFIRMED) && paymentStatus.equals (PaymentStatus.CONFIRMED)){
            orderPublisher.updateProductShipOut (getProductsFromOrder (order));
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
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setPaymentStatus (PaymentStatus.PENDING);
        return order;
    }

    @Override
    @Async
    public CompletableFuture<OrderDto> requestPaymentOrder (OrderDto orderDto) {
        orderPublisher.placeOrderMessage (orderDto);
        return CompletableFuture.completedFuture(orderDto);

    }


}
