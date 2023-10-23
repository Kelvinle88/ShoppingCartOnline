package osc.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import osc.dto.ProductDto;
import osc.enums.ProductStatus;
import osc.entity.Product;
import osc.entity.ShipIn;
import osc.entity.ShipOut;
import osc.enums.ShipEventType;
import osc.mapper.ProductMapper;
import osc.repository.ProductRepository;
import osc.repository.ShipInRepository;
import osc.repository.ShipOutRepository;
import osc.security.AuthHelper;
import osc.service.ProductService;

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
        //product.setProductType (productDto.getProductType ());
        product.setProductPrice (productDto.getProductPrice ());
        //product.setDescription (productDto.getDescription ());
        //product.setCategory (productDto.getCategory ());
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
            shipIn.setShipEventType (ShipEventType.WAREHOUSE);
            shipIn.setShipmentDate (LocalDateTime.now ());
            shipInRepository.save (shipIn);
        }

        return productMapper.toDto (product);
    }
    @Transactional
    @Override
    public List<ProductDto> updateProductShipOut(List<ProductDto> productDtos) {
        for(ProductDto productDto : productDtos){
            Product exist = productRepository.findById (productDto.getProductId ()).orElse (null);
            int availableQuantity = exist.getAvailableQuantity () - productDto.getQuantity ();
            exist.setAvailableQuantity (availableQuantity);
            ShipOut shipOut = new ShipOut ();
            shipOut.setProduct (exist);
            shipOut.setQuantity (productDto.getQuantity ());
            shipOut.setShipEventType (ShipEventType.ORDERED);
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
    @Transactional
    @Override
    public List<ProductDto> updateOrderCancel(List<ProductDto> productDtos) {
        for(ProductDto productDto : productDtos){
            Product exist = productRepository.findById (productDto.getProductId ()).orElse (null);
            int availableQuantity = exist.getAvailableQuantity () + productDto.getQuantity ();
            exist.setAvailableQuantity (availableQuantity);
            ShipIn shipIn = new ShipIn ();
            shipIn.setProduct (exist);
            shipIn.setQuantity (productDto.getQuantity ());
            shipIn.setShipEventType (ShipEventType.CANCELED);
            shipIn.setShipmentDate (LocalDateTime.now ());
            shipInRepository.save (shipIn);
            productRepository.save (exist);
        }
        return productDtos;

    }
}
