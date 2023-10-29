package osc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    public static final List <String> SWAGGER_PATH = new ArrayList <> () {{
        add ("/v3/api-docs");
        add ("/swagger-ui/**");
        add ("/swagger-ui.html");
        add ("/swagger-resources/**");
        add ("/webjars/swagger-ui/**");
    }};

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (final ServerHttpSecurity http) throws Exception {
        http
                .cors (corsSpec -> {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource ();
                    CorsConfiguration config = new CorsConfiguration ();
                    config.applyPermitDefaultValues ();
                    config.setAllowedOrigins (Arrays.asList ("*"));
                    config.setAllowedHeaders (Arrays.asList ("*"));
                    config.setAllowedMethods (Arrays.asList ("POST","GET","DELETE","PUT"));
                    config.setExposedHeaders (Arrays.asList ("content-length"));
                    config.setMaxAge (3600L);
                    source.registerCorsConfiguration ("/**",config);
                    corsSpec.configurationSource (source);
                }).csrf ().disable ()
                .authorizeExchange (exchanges -> exchanges
                        .pathMatchers ("/swagger-ui.html","/webjars/**","/v3/api-docs/**","/swagger-resources/**").permitAll ()
                        .pathMatchers ("/**").permitAll ()
                        .pathMatchers ("/uaa/**").permitAll ()
                        .anyExchange ().authenticated ());

        return http.build ();
    }

}
