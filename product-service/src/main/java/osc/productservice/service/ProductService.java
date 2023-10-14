package osc.productservice.service;

import osc.productservice.dto.ProductDto;
import osc.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(Product product);
    ProductDto updateProduct(Long productId,ProductDto productDto);
    void deleteProduct(Long id);
    List <ProductDto> findAll ();
    public ProductDto updateProductShipIn(Long productId);
    public ProductDto updateProductShipOut(Long productId);
    ProductDto getProductByProductId(Long productId);
}
