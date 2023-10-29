package osc.controler;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    public List <ShipinDto> findByShippingDate(@PathVariable @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime shippingDate){
        return shipinService.findByShippingDate(shippingDate);
    }
    @GetMapping("/{startDate}/{endDate}")
    public List <ShipinDto> findByShipmentDateBetween (@PathVariable @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime startDate,
                                                       @PathVariable @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime endDate){
        return shipinService.findByShipmentDateBetween(startDate,endDate);
    }
}
