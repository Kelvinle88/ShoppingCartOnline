package osc.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productType;
    private Date dateAdded;
    private String productName;
    private BigDecimal productPrice;
    private int category;
    private String description;
    @Lob
    private String imageData;
    private String vendorId;
    private String status;
    private int availableQuantity;

    @OneToMany (mappedBy = "product",cascade = CascadeType.ALL)
    private List <ShipOut> shipOuts;

    @OneToMany (mappedBy = "product",cascade = CascadeType.ALL)
    private List<ShipIn> shipIns;
}
