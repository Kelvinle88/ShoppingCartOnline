package osc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osc.service.PaymentService;

@RestController
//@CrossOrigin(origins = "http://frondend")
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @PostMapping()
//    public void Checkout (@RequestBody OrderDTO orderDTO) throws StripeException {
//             paymentService.CheckOut (orderDTO);
//    }
}

