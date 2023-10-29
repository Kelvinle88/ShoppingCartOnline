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
@Table(name = "shipOut")
@Entity
public class ShipOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipOutId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate shipmentDate;
    private int quantity;
    private ShipEventType shipEventType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

}

