package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import osc.entity.ShipIn;

@Repository
public interface ShipInRepository extends JpaRepository <ShipIn, Long> {
    @Query("SELECT SUM(si.quantity) FROM ShipIn si  WHERE si.product.productId = :productId")
    int getTotalShipInQuantityForProduct(@Param("productId") Long productId);
}

