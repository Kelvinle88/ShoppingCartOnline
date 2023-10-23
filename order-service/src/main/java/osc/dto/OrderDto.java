package osc.dto;

import lombok.*;
import osc.constant.OrderStatus;
import osc.constant.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {
    private Long id;
    private LocalDate orderedDate;
    private String userId;
    private BigDecimal total;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private List <ItemDto> items;
}
