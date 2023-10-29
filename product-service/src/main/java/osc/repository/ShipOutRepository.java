package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osc.entity.ShipOut;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipOutRepository extends JpaRepository <ShipOut, Long> {
    List <ShipOut> findByShipmentDate(LocalDateTime shipmentDate);
    List <ShipOut> findByShipmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
