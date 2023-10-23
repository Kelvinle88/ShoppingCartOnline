package osc.events;

import lombok.*;
import osc.constant.EventType;
import osc.dto.OrderDto;
import osc.dto.ProductDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OrderEvent {
    private EventType eventType;
    private List <ProductDto> productDtoList;
    private OrderDto orderDto;

    public OrderEvent (EventType eventType,List <ProductDto> productDtoList) {
        this.eventType = eventType;
        this.productDtoList = productDtoList;
    }

    public OrderEvent (EventType eventType,OrderDto orderDto) {
        this.eventType = eventType;
        this.orderDto = orderDto;
    }
}
