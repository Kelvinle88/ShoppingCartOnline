package osc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.dto.ShipoutDto;
import osc.mapper.ShipoutMapper;
import osc.repository.ShipOutRepository;
import osc.service.ShipoutService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipoutServiceImpl implements ShipoutService {
    @Autowired
    private ShipOutRepository shipOutRepository;
    @Autowired
    private ShipoutMapper shipoutMapper;
    @Override
    public List <ShipoutDto> findAll () {
        return shipOutRepository.findAll ().stream()
                .map(p -> shipoutMapper.toDto ((p)))
                .toList();
    }

    @Override
    public List <ShipoutDto> findByShippingDate (LocalDateTime shippingDate) {
        return shipOutRepository.findByShipmentDate (shippingDate).stream()
                .map(p -> shipoutMapper.toDto ((p)))
                .toList();
    }
    @Override
    public List <ShipoutDto> findByShipmentDateBetween (LocalDateTime startDate,LocalDateTime endDate) {
        return shipOutRepository.findByShipmentDateBetween (startDate,endDate).stream()
                .map(p -> shipoutMapper.toDto ((p)))
                .toList();
    }
}
