package osc.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import osc.dto.ItemDto;
import osc.entity.Item;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;

//    public ItemDto toDto (Item item) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // Define the mapping from UserDto to User
//        modelMapper.createTypeMap(Item.class, ItemDto.class)
//                .addMapping(Item::getId, ItemDto::setId)
//                .addMapping(Item::getQuantity, ItemDto::setQuantity)
//                .addMapping(Item::getSubTotal, ItemDto::setSubTotal)
//                .addMapping (Item::getProduct,ItemDto::setProductDto);
//        return modelMapper.map (item,ItemDto.class);
//    }
    public ItemDto toDto(Item item) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Define the mapping from Item to ItemDto
        modelMapper.typeMap(Item.class, ItemDto.class)
                .addMapping(Item::getId, ItemDto::setId)
                .addMapping(Item::getQuantity, ItemDto::setQuantity)
                .addMapping(Item::getSubTotal, ItemDto::setSubTotal)
                .addMappings(mapper -> mapper.skip(ItemDto::setProductDto)); // Skip the productDto mapping for now

        ItemDto itemDto = modelMapper.map(item, ItemDto.class);

        // Set the ProductDto in the ItemDto
        if (item.getProduct() != null) {
            itemDto.setProductDto(productMapper.toDto(item.getProduct()));
        }

        return itemDto;
    }


//    public User toEntity (UserDto userDto) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // Define the mapping from UserDto to User
//        modelMapper.createTypeMap(UserDto.class, User.class)
//                .addMapping(UserDto::getId, User::setId)
//                .addMapping(UserDto::getName, User::setUserName);
//        return modelMapper.map (userDto,User.class);
//    }

}

