package osc.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import osc.dto.OrderDto;
import osc.entity.Order;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;
    public OrderDto toDto (Order order) {
        return modelMapper.map (order,OrderDto.class);
    }

    public Order toEntity (OrderDto orderDto) {
        return modelMapper.map (orderDto,Order.class);
    }

}

