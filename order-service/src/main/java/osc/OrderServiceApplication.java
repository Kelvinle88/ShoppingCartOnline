package osc;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableWebSecurity
@EnableRedisHttpSession
public class OrderServiceApplication {

    public static void main (String[] args) {
        SpringApplication.run (OrderServiceApplication.class,args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
