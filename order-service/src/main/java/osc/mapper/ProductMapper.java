package osc.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import osc.dto.ProductDto;
import osc.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;
    public ProductDto toDto (Product product) {
        return modelMapper.map (product,ProductDto.class);
    }

    public Product toEntity (ProductDto productDto) {
        return modelMapper.map (productDto,Product.class);
    }

}

