package osc;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
//@EnableJpaRepositories
//@ImportAutoConfiguration({FeignAutoConfiguration.class})
//@EnableDiscoveryClient
@EnableEurekaClient
public class ProductServiceApplication {

    public static void main (String[] args) {
        SpringApplication.run (ProductServiceApplication.class,args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
