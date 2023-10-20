package osc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "shipIn")
@Entity
public class ShipIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipInId;
    private LocalDateTime shipmentDate;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
