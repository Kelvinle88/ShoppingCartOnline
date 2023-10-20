package osc.dto;

import lombok.*;
import osc.constant.OrderStatus;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {
    private Long id;
    private String userId;
    private BigDecimal total;
    private OrderStatus status;


}
