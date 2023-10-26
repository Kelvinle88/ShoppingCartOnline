package osc.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import osc.dto.ShipinDto;
import osc.entity.ShipIn;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShipinMapper {
    private final ModelMapper modelMapper;

    public ShipinDto toDto (ShipIn shipIn) {
        return modelMapper.map (shipIn,ShipinDto.class);
    }

    public List <ShipinDto> toDtos (List <ShipIn> shipins) {
        return shipins.stream ()
                .map (this::toDto)
                .toList ();
    }

    public ShipIn toEntity (ShipinDto shipinDto) {
        return modelMapper.map (shipinDto,ShipIn.class);
    }

    public List <ShipIn> toEntities (List <ShipinDto> shipinDtos) {
        return shipinDtos.stream ()
                .map (this::toEntity)
                .toList ();
    }
}

