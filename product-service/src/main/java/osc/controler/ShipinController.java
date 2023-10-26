package osc.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import osc.dto.ShipinDto;
import osc.service.ShipinService;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/shipin")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ShipinController {
    @Autowired
    private final ShipinService shipinService;

    @GetMapping
    public List <ShipinDto> findAll(){
        return shipinService.findAll();
    }
    @GetMapping("/{shippingDate}")
    public List <ShipinDto> findByShippingDate(@PathVariable LocalDateTime shippingDate){
        return shipinService.findByShippingDate(shippingDate);
    }
    @GetMapping("/{startDate}/{endDate}")
    public List <ShipinDto> findByShipmentDateBetween (@PathVariable LocalDateTime startDate,
                                                       @PathVariable LocalDateTime endDate){
        return shipinService.findByShipmentDateBetween(startDate,endDate);
    }
}
