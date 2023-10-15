package osc.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import osc.productservice.entity.ShipOut;

@Repository
public interface ShipOutRepository extends JpaRepository <ShipOut, Long> {
    @Query("SELECT SUM(so.quantity) FROM ShipOut so WHERE so.product.productId = :productId")
    int getTotalShipOutQuantityForProduct(@Param("productId") Long productId);
}
