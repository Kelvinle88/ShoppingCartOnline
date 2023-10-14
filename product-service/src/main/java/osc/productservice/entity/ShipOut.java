package osc.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "shipOut")
@Entity
public class ShipOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipOutId;
    private Date shipmentDate;
    private int quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "productId",name = "product_id")
    private Product product;

}

