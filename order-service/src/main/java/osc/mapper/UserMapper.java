package osc.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import osc.dto.UserDto;
import osc.entity.User;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDto toDto (User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Define the mapping from UserDto to User
        modelMapper.createTypeMap(User.class, UserDto.class)
                .addMapping(User::getId, UserDto::setId)
                .addMapping(User::getUserName, UserDto::setName);
        return modelMapper.map (user,UserDto.class);
    }

    public User toEntity (UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Define the mapping from UserDto to User
        modelMapper.createTypeMap(UserDto.class, User.class)
                .addMapping(UserDto::getId, User::setId)
                .addMapping(UserDto::getName, User::setUserName);
        return modelMapper.map (userDto,User.class);
    }

}

