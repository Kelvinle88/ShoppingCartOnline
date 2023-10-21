package osc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonIgnore
    private Long productId;

//    @Transient
//    private Long id;

    @Column (name = "product_name")
    @NotNull
    private String productName;

    @Column (name = "price")
    @NotNull
    private BigDecimal productPrice;

    @Column
    private int quantity;

    @OneToMany (mappedBy = "product", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<Item> items;
}
