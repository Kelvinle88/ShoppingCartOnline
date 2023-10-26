package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osc.entity.ShipIn;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipInRepository extends JpaRepository <ShipIn, Long> {
    List <ShipIn> findByShipmentDate(LocalDateTime shipmentDate);
    List <ShipIn> findByShipmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}

