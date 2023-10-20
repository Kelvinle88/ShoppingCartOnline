package osc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import osc.dto.ProductDto;

@FeignClient(name = "PRODUCT-SERVICE")
//@LoadBalancerClient(name = "PRODUCT-SERVICE")
public interface ProductFeignClient {
    @GetMapping(value="/products/{productId}")
    //@Headers("Content-Type: application/json")
   // @HandleFeignException(UserConsumerExceptionHandler.class)
    ProductDto getProductByProductId(@PathVariable("productId") Long productId);

    //@Headers("Content-Type: application/json")
    //@PostMapping(value = "/products/{productId}/order")
   // @HandleFeignException(UserConsumerExceptionHandler.class)
   // ProductDto updateProductShipOut(@PathVariable("productId") Long productId,@RequestParam int orderQuantity);
//    @PostMapping(value = "/products/update")
//    List <ProductDto> updateProductShipOut(@RequestBody List<ProductDto> products);

}
