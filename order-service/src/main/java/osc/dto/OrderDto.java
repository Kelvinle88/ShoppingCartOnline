package osc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import osc.constant.OrderStatus;
import osc.constant.PaymentStatus;
import osc.entity.Item;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderedDate;
    private String userId;
    private BigDecimal total;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private List <Item> items;
}
