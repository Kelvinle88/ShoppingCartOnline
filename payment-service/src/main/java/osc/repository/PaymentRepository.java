package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osc.entity.Payment;

public interface PaymentRepository extends JpaRepository <Payment, String> {
    Payment findByOrderId (Long id);
}
