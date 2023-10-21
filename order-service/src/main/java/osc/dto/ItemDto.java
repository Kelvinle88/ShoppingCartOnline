package osc.dto;

import lombok.*;
import osc.entity.Product;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemDto {
    private Long id;
    private int quantity;
    private BigDecimal subTotal;
    private Product product;
}
