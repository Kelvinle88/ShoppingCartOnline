package osc.events;

import lombok.*;
import osc.enums.EventType;
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

}
