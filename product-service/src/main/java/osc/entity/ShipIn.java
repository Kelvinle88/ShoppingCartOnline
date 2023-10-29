package osc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import osc.enums.ShipEventType;

import javax.persistence.*;
import java.time.LocalDate;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate shipmentDate;
    private int quantity;
    private ShipEventType shipEventType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
