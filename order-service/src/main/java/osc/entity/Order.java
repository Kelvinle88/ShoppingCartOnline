package osc.entity;

import com.sun.istack.NotNull;
import lombok.*;
import osc.constant.OrderStatus;
import osc.constant.PaymentStatus;

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

    @Column(name = "order_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "payment_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column (name = "total")
    private BigDecimal total;

    @Column (name = "totalQuantity")
    private int totalQuantity;

    @Column(name = "user_id")
    private String userId;
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "cart" , joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn (name = "item_id"))
    private List<Item> items;

}
