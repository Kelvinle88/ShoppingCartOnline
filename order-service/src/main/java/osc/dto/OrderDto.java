package osc.dto;

import lombok.*;
import osc.constant.OrderStatus;
import osc.constant.PaymentStatus;
import osc.entity.Item;

import java.math.BigDecimal;
import java.util.List;

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
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private List <Item> items;

    public OrderDto (Long id,String userId,BigDecimal total,OrderStatus orderStatus,PaymentStatus paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }
}
