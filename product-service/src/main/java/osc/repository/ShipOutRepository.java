package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osc.entity.ShipOut;

@Repository
public interface ShipOutRepository extends JpaRepository <ShipOut, Long> {

}
