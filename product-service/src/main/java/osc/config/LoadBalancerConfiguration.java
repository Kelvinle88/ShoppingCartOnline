package osc.config;

//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.cloud.client.discovery.DiscoveryClient;

//@Slf4j
//@Configuration
//public class LoadBalancerConfiguration {
//    @Bean
//    public ServiceInstanceListSupplier
//    discoveryClientServiceInstanceListSupplier(
//            ConfigurableApplicationContext context) {
//        log.info("Configuring Load balancer to prefer the same instance");
//        return ServiceInstanceListSupplier.builder()
//                .withBlockingDiscoveryClient()
//                .withSameInstancePreference()
//                .build(context);
//    }
//}
