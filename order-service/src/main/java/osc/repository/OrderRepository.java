package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osc.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    List <Order> findAllByUserId(String userId);
}
