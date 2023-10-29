package osc.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import osc.dto.ShipoutDto;
import osc.entity.ShipOut;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShipoutMapper {
    private final ModelMapper modelMapper;

    public ShipoutDto toDto (ShipOut shipOut) {
        return modelMapper.map (shipOut,ShipoutDto.class);
    }

    public List <ShipoutDto> toDtos (List <ShipOut> shipOuts) {
        return shipOuts.stream ()
                .map (this::toDto)
                .toList ();
    }

    public ShipOut toEntity (ShipoutDto shipoutDto) {
        return modelMapper.map (shipoutDto,ShipOut.class);
    }

    public List <ShipOut> toEntities (List <ShipoutDto> shipoutDtos) {
        return shipoutDtos.stream ()
                .map (this::toEntity)
                .toList ();
    }
}

