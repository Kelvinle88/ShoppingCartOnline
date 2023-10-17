package osc.productservice.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import osc.productservice.dto.ProductDto;
import osc.productservice.emun.ProductStatus;
import osc.productservice.entity.Product;
import osc.productservice.entity.ShipIn;
import osc.productservice.entity.ShipOut;
import osc.productservice.mapper.ProductMapper;
import osc.productservice.repository.ProductRepository;
import osc.productservice.repository.ShipInRepository;
import osc.productservice.repository.ShipOutRepository;
import osc.productservice.security.AuthHelper;
import osc.productservice.service.ProductService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final AuthHelper authHelper;
    private final ShipInRepository shipInRepository;
    private final ShipOutRepository shipOutRepository;

    @Transactional
    @Override
    public ProductDto createProduct(Product product) {
        product.setVendorId (authHelper.getUserId ());
        productRepository.save (product);
        return productMapper.toDto (product);
    }

    @Transactional
    @Override
    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        String vendorId = authHelper.getUserId ();
        Product product = productRepository.findByVendorIdAndProductId(vendorId, productId);
        // Check if the product exists
        if (product == null) {
            throw new RuntimeException("Product with Vendor Id: " + vendorId + " and Product Id: " + productId + " does not exist.");
        }
        // Update the product entity with the new data
        product.setProductName(productDto.getProductName ());
        product.setProductType (productDto.getProductType ());
        product.setProductPrice (productDto.getProductPrice ());
        product.setDescription (productDto.getDescription ());
        product.setCategory (productDto.getCategory ());
        // Save the updated product to the database
        productRepository.save(product);
        // Map and return the updated product as a DTO
        return productMapper.toDto(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow ();
        product.setStatus (String.valueOf (ProductStatus.UNAVAILABLE));
        productRepository.save (product);
    }

    @Override
    public List <ProductDto> findAll () {
        return productRepository.findAll ().stream()
                .map(p -> productMapper.toDto ((p)))
                .toList();
    }
    @Transactional
    @Override
    public ProductDto updateProductShipIn(Long productId,int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException ("Product not found"));
        if (product != null) {
            int availableQuantity = product.getAvailableQuantity() + quantity;
            product.setAvailableQuantity(availableQuantity);
            productRepository.save(product);
            ShipIn shipIn = new ShipIn ();
            shipIn.setProduct (product);
            shipIn.setQuantity (quantity);
            shipIn.setShipmentDate (LocalDateTime.now ());
            shipInRepository.save (shipIn);
        }

        return productMapper.toDto (product);
    }
    @Transactional
    @Override
    public List<ProductDto> updateProductShipOut(List<ProductDto> productDtos) {
        //List<Product> productList = productMapper.toEntities (productDtos);
        for(ProductDto productDto: productDtos){
            Product exist = productRepository.findById (productDto.getProductId ()).orElse (null);
            int avalaibleQuantity = exist.getAvailableQuantity () - productDto.getQuantity ();
            exist.setAvailableQuantity (avalaibleQuantity);
            ShipOut shipOut = new ShipOut ();
            shipOut.setProduct (exist);
            shipOut.setQuantity (productDto.getQuantity ());
            shipOut.setShipmentDate (LocalDateTime.now ());
            shipOutRepository.save (shipOut);
            productRepository.save (exist);
        }
        return productDtos;

    }

    @Override
    public ProductDto getProductByProductId (Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return productMapper.toDto (product);
    }

    @Override
    public List <ProductDto> getProductByVendorId (String vendorId) {
        return productRepository.findByVendorId(vendorId)
                .stream()
                .map(p -> productMapper.toDto ((p)))
                .toList();
    }
}
