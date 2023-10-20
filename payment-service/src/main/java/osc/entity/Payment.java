package osc.entity;

import lombok.*;
import osc.enums.PaymentStatus;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    private String paymentId;
    private Long orderId;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus status;
}
