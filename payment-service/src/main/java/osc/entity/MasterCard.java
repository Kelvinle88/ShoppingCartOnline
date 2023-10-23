package osc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@DiscriminatorValue("MasterCard")
public class MasterCard extends PaymentInfo {
    private String cvv; // Card Verification Value for MasterCard
}

