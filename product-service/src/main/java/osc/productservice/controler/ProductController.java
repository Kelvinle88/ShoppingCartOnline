package osc.productservice.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import osc.productservice.dto.ProductDto;
import osc.productservice.entity.Product;
import osc.productservice.service.ProductService;

import java.util.List;

@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List <ProductDto> findAll(){
        return productService.findAll();
    }
    @GetMapping("/{productId}")
    ProductDto getProductByProductId(@PathVariable("productId") Long productId){
        return productService.getProductByProductId(productId);
    }
    @GetMapping("/vendor/{vendorId}")
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<List<ProductDto>> getProductByVendorId
            (@PathVariable("vendorId") String vendorId){
        List<ProductDto> productDtos =  productService.getProductByVendorId(vendorId);
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody Product product) {
             System.out.println ("coming create");
            ProductDto createdProduct = productService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductDto productDto) {
            ProductDto updatedProduct = productService.updateProduct(productId, productDto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId ) {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{productId}/shipping")
    @PreAuthorize("hasRole('ROLE_VENDOR') ")
    public ResponseEntity<ProductDto> updateProductShipIn(@PathVariable Long productId){
        ProductDto updatedProductQuantity = productService.updateProductShipIn (productId);
        return new ResponseEntity<> (updatedProductQuantity,HttpStatus.OK);
    }
    @PostMapping("/{productId}/order")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<ProductDto> updateProductShipOut(@PathVariable Long productId){
        ProductDto updatedProductQuantity = productService.updateProductShipOut (productId);
        return new ResponseEntity<> (updatedProductQuantity,HttpStatus.OK);
    }
}
