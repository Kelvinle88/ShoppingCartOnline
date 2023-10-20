package osc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.constant.OrderStatus;
import osc.dto.ProductDto;
import osc.entity.Order;
import osc.mapper.OrderMapper;
import osc.publisher.OrderPublisher;
import osc.repository.OrderRepository;
import osc.service.OrderService;

import javax.transaction.Transactional;
import java.util.*;

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
    public void updateOrderStatus (Long id,OrderStatus status) {
        Order order = orderRepository.findById (id).orElse (null);
        order.setStatus (status);
        orderRepository.save (order);
        if(status.equals (OrderStatus.CONFIRMED)){
            orderPublisher.updateProductShipOut (getProductsFromOrder (order));
        }
        //return orderMapper.toDto (order);
    }

    @Override
    public Order getOrder(Long id){
        return orderRepository.findById (id).orElse (null);
    }

}
