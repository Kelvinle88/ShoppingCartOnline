package osc.dto;

import lombok.*;
import osc.enums.OrderStatus;
import osc.enums.PaymentStatus;

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
    private String userId;
    private LocalDate orderedDate;
    private BigDecimal total;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private List <ItemDto> items;
}
