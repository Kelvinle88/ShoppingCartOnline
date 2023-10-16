package osc.orderservice.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import osc.orderservice.dto.ProductDto;
import osc.orderservice.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;
    
//    public ProductDto toDto (Product product) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // Define the mapping from ProductDto to Product
//        modelMapper.createTypeMap(Product.class, ProductDto.class)
//                .addMapping(Product::getId, ProductDto::setProductId)
//                .addMapping(Product::getProductName, ProductDto::setProductName);
//        return modelMapper.map (product,ProductDto.class);
//    }
    public ProductDto toDto (Product product) {
        return modelMapper.map (product,ProductDto.class);
    }

//    public Product toEntity (ProductDto productDto) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // Define the mapping from ProductDto to Product
//        modelMapper.createTypeMap(ProductDto.class, Product.class)
//                .addMapping(ProductDto::getProductId, Product::setId)
//                .addMapping(ProductDto::getProductName, Product::setProductName);
//        return modelMapper.map (productDto,Product.class);
//    }
    public Product toEntity (ProductDto productDto) {
        return modelMapper.map (productDto,Product.class);
    }

}

