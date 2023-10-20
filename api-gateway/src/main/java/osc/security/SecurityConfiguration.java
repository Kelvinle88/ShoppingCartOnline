package osc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.ArrayList;
import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    public static final List<String> SWAGGER_PATH = new ArrayList<>() {{
        add("/v3/api-docs");
        add("/swagger-ui/**");
        add("/swagger-ui.html");
        add("/swagger-resources/**");
        add("/webjars/swagger-ui/**");
    }};
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                                .pathMatchers("/swagger-ui.html", "/webjars/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                                .pathMatchers ("/**").permitAll ()
                                .anyExchange().authenticated())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


}
