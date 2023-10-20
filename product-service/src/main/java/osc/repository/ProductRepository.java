package osc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osc.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    Product findByVendorIdAndProductId (String vendorId,Long productId);
    List<Product> findByVendorId(String vendorId);

}
