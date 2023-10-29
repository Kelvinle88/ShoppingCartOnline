package osc.controler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import osc.dto.ShipoutDto;
import osc.service.ShipoutService;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/shipout")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ShipoutController {
    @Autowired
    private final ShipoutService shipoutService;

    @GetMapping
    public List <ShipoutDto> findAll(){
        return shipoutService.findAll();
    }
    @GetMapping("/{shipoutDate}")
    public List <ShipoutDto> findByShippingDate(@PathVariable @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime shippingDate){
        return shipoutService.findByShippingDate(shippingDate);
    }
    @GetMapping("/{startDate}/{endDate}")
    public List <ShipoutDto> findByShipmentDateBetween (@PathVariable @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime startDate,
                                                       @PathVariable @JsonFormat(pattern="yyyy-MM-dd") LocalDateTime endDate){
        return shipoutService.findByShipmentDateBetween(startDate,endDate);
    }
}
