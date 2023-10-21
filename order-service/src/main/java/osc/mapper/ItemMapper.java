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

    public ItemDto toDto (Item item) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Define the mapping from UserDto to User
        modelMapper.createTypeMap(Item.class, ItemDto.class)
                .addMapping(Item::getId, ItemDto::setId)
                .addMapping(Item::getQuantity, ItemDto::setQuantity)
                .addMapping(Item::getSubTotal, ItemDto::setSubTotal)
                .addMapping (Item::getProduct,ItemDto::setProduct);
        return modelMapper.map (item,ItemDto.class);
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

