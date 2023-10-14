package osc.orderservice.client;

import feign.Headers;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import osc.orderservice.dto.ProductDto;

@FeignClient(name = "PRODUCT-SERVICE",url = "PRODUCT-SERVICE")
@LoadBalancerClient(name = "PRODUCT-SERVICE")
public interface ProductFeignClient {
    @Headers("Content-Type: application/json")
    @GetMapping(value = "/products/{productId}", produces = "application/json")
   // @HandleFeignException(UserConsumerExceptionHandler.class)
    ProductDto getProductByProductId(@PathVariable("productId") Long productId);

    @Headers("Content-Type: application/json")
    @PostMapping(value = "/{productId}/order", produces = "application/json")
   // @HandleFeignException(UserConsumerExceptionHandler.class)
    ProductDto updateProductShipOut(@PathVariable("productId") Long productId);

}
