package osc.service;

import osc.dto.ShipoutDto;

import java.time.LocalDateTime;
import java.util.List;


public interface ShipoutService {
    public List <ShipoutDto> findAll();
    public List <ShipoutDto> findByShippingDate(LocalDateTime shippingDate);
    public List <ShipoutDto> findByShipmentDateBetween (LocalDateTime startDate,LocalDateTime endDate);
}
