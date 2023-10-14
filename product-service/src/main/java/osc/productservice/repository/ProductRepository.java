package osc.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osc.productservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    Product findByVendorIdAndProductId (String vendorId,Long productId);
}
