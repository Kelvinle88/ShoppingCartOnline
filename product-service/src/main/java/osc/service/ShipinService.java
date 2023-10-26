package osc.service;

import osc.dto.ShipinDto;

import java.time.LocalDateTime;
import java.util.List;


public interface ShipinService {
    public List <ShipinDto> findAll();
    public List <ShipinDto> findByShippingDate(LocalDateTime shippingDate);
    public List <ShipinDto> findByShipmentDateBetween (LocalDateTime startDate,LocalDateTime endDate);
}
