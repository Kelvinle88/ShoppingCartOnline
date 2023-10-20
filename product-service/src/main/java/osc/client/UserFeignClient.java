//package osc.productservice.client;
//
//import feign.Headers;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import osc.productservice.dto.ProductDto;
//import osc.productservice.dto.UserDto;
//
//import java.util.List;
//
//@FeignClient(name = "USER-SERVICE",url = "USER-SERVICE")
//@LoadBalancerClient(name = "USER-SERVICE")
//public interface UserFeignClient {
//
//    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}")
//    List <ProductDto> findByUserId(@PathVariable("userId") String userId);
//
//    @Headers("Content-Type: application/json")
//    @GetMapping(value = "/users/{userId}", produces = "application/json")
//   // @HandleFeignException(UserConsumerExceptionHandler.class)
//    UserDto getUserById(@PathVariable("userId") String userId);
//
//}
