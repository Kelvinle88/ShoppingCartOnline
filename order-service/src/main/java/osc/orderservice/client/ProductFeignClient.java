package osc.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import osc.orderservice.dto.ProductDto;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
//@LoadBalancerClient(name = "PRODUCT-SERVICE")
public interface ProductFeignClient {
//    @Headers("Content-Type: application/json")
//    @GetMapping(value = "/products/{productId}", produces = "application/json")
    @GetMapping(value="/products/{productId}")
    //@Headers("Content-Type: application/json")
   // @HandleFeignException(UserConsumerExceptionHandler.class)
    ProductDto getProductByProductId(@PathVariable("productId") Long productId);

    //@Headers("Content-Type: application/json")
    //@PostMapping(value = "/products/{productId}/order")
   // @HandleFeignException(UserConsumerExceptionHandler.class)
   // ProductDto updateProductShipOut(@PathVariable("productId") Long productId,@RequestParam int orderQuantity);
    @PostMapping(value = "/products/update")
    List <ProductDto> updateProductShipOut(@RequestBody List<ProductDto> products);

}
