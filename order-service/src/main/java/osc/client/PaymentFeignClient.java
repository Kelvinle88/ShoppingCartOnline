package osc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import osc.entity.Order;

@FeignClient(name = "PAYMENT-SERVICE")
//@LoadBalancerClient(name = "PRODUCT-SERVICE")
public interface PaymentFeignClient {
     @PostMapping(value = "/payment")
    String Checkout (@RequestBody Order order);

}
