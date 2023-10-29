package osc.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true,
        jsr250Enabled = true,
        securedEnabled = true
)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
private final JwtFilter jwtFilter;

    public static final List<String> SWAGGER_PATH = new ArrayList<>() {{
        add("/v3/api-docs");
        add("/swagger-ui/**");
        add("/swagger-ui.html");
        add("/swagger-resources/**");
        add("/webjars/swagger-ui/**");
    }};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsSpec ->{
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

                })
                //.and()
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers ("/products/**").permitAll ()
                .antMatchers (HttpMethod.GET,"/products/**").permitAll()
                .antMatchers(HttpMethod.POST,"/products/**").hasAnyRole("ADMIN", "VENDOR")
                .antMatchers(HttpMethod.PUT,"/products/**").hasAnyRole("ADMIN", "VENDOR")
                .antMatchers(HttpMethod.DELETE,"/products/**").hasAnyRole("ADMIN", "VENDOR")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private String[] getPrefixPaths(List<String> paths, String prefix) {
//        List<String> result = paths;
//        if (StringUtils.isNotBlank(prefix)) {
//            result = paths.stream().map(s -> String.format("/%s%s", prefix, s)).toList();
//        }
//        return result.toArray(new String[0]);
//    }
}
