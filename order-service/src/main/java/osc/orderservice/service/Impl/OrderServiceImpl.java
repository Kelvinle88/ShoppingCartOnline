package osc.orderservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.orderservice.dto.ProductDto;
import osc.orderservice.entity.Order;
import osc.orderservice.repository.OrderRepository;
import osc.orderservice.service.OrderService;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

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

}
