package osc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.dto.ShipinDto;
import osc.mapper.ShipinMapper;
import osc.repository.ShipInRepository;
import osc.service.ShipinService;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ShipinServiceImpl implements ShipinService {
    @Autowired
    private ShipInRepository shipInRepository;
    @Autowired
    private ShipinMapper shipinMapper;
    @Override
    public List <ShipinDto> findAll () {
        return shipInRepository.findAll ().stream()
                .map(p -> shipinMapper.toDto ((p)))
                .toList();
    }

    @Override
    public List <ShipinDto> findByShippingDate (LocalDateTime shippingDate) {
        return shipInRepository.findByShipmentDate (shippingDate).stream()
                .map(p -> shipinMapper.toDto ((p)))
                .toList();
    }
    @Override
    public List <ShipinDto> findByShipmentDateBetween (LocalDateTime startDate,LocalDateTime endDate) {
        return shipInRepository.findByShipmentDateBetween (startDate,endDate).stream()
                .map(p -> shipinMapper.toDto ((p)))
                .toList();
    }
}
