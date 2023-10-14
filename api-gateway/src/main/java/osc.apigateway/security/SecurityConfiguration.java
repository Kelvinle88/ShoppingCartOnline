package osc.apigateway.security;

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
                                .pathMatchers ("/**").permitAll ()
//                        .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, null)).permitAll()
//                        .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "user-service")).permitAll()
//                        .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "product-service")).permitAll()
//                        .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "order-service")).permitAll()
//                        .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "report-service")).permitAll()
//                        .pathMatchers(this.getPrefixPaths(SWAGGER_PATH, "email-service")).permitAll()
//                        .pathMatchers("/users/login/**").permitAll()
//                        .pathMatchers("/user-service/login/**").permitAll()
//                        .pathMatchers("/user/api/v1/register").permitAll()
//                        .pathMatchers(HttpMethod.GET, "/product-service/products/**", "/products/**").permitAll()
//                        .pathMatchers(HttpMethod.GET, "/order-service/orders/**", "/orders/**").hasRole ("USER")
                        .anyExchange().authenticated()
                )
                .csrf(csrf -> csrf.disable());
               // .oauth2Login()
               // .and()
//                .oauth2ResourceServer(oauth2 ->
//                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
//                        ));
        return http.build();
    }

//    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//    }
//
//    private String[] getPrefixPaths(List<String> paths, String prefix) {
//        List<String> result = paths;
//        if (StringUtils.isNotBlank(prefix)) {
//            result = paths.stream().map(s -> String.format("/%s%s", prefix, s)).toList();
//        }
//        return result.toArray(new String[0]);
//    }
}
