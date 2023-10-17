package osc.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long productId;
    private String productType;
    private String productName;
    private BigDecimal productPrice;
    private int category;
    private String description;
    private int quantity;

}
