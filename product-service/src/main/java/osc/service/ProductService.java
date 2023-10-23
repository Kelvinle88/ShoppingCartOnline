package osc.service;

import osc.dto.ProductDto;
import osc.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(Product product);
    ProductDto updateProduct(Long productId,ProductDto productDto);
    void deleteProduct(Long id);
    List <ProductDto> findAll ();
    public ProductDto updateProductShipIn(Long productId,int quantity);
    public List<ProductDto> updateProductShipOut(List<ProductDto> productDtos);
    ProductDto getProductByProductId(Long productId);

    List<ProductDto> getProductByVendorId (String vendorId);
    public List<ProductDto> updateOrderCancel(List<ProductDto> productDtos);
}
