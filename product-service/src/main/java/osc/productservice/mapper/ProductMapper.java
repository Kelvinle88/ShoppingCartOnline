package osc.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import osc.productservice.dto.ProductDto;
import osc.productservice.entity.Product;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductDto toDto (Product product) {
        return modelMapper.map (product,ProductDto.class);
    }

    public List <ProductDto> toDtos (List <Product> productLists) {
        return productLists.stream ()
                .map (this::toDto)
                .toList ();
    }

    public Product toEntity (ProductDto productDto) {
        return modelMapper.map (productDto,Product.class);
    }

    public List <Product> toEntities (List <ProductDto> productDtos) {
        return productDtos.stream ()
                .map (this::toEntity)
                .toList ();
    }
}

