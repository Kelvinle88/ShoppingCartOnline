package osc.dto;

//import jakarta.persistence.Entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;

//    private Long id;
//    private String productName;
//    private BigDecimal productPrice;
}
