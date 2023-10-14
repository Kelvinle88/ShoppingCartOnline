package osc.orderservice.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ordered_date")
    @NotNull
    private LocalDate orderedDate;

    @Column(name = "status")
    @NotNull
    private String status;

    @Column (name = "total")
    private BigDecimal total;

    @Column(name = "user_id")
    private String userId;
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "cart" , joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn (name = "item_id"))
    private List<Item> items;

//    @ManyToOne (cascade = CascadeType.ALL)
//    @JoinColumn (name = "user_id")
//    private User user;
}
