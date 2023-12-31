package osc.security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

//    private final JwtHelper jwtHelper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        final String authorizationHeader = request.getHeader("Authorization");
//        String method = request.getMethod();
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            var token = authorizationHeader.substring(7);
//            boolean isTokenValid = false;
//            //boolean isTokenValid = true;
//            isTokenValid = jwtHelper.validateToken(token);
//            var claims = jwtHelper.getUserIdFromToken(token);
//            if (isTokenValid && SecurityContextHolder.getContext().getAuthentication() == null) {
//                var userDetails = new AwesomeUserDetails(claims);
//                userDetails.setToken (token);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//
//                //STORE IN the CONTEXT
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
private final JwtHelper jwtHelper;

    private final ModelMapper modelMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring(7);
            var isValidToken = jwtHelper.validateToken(token);

            var auth = SecurityContextHolder.getContext().getAuthentication();

            if (isValidToken && (auth == null || auth instanceof AnonymousAuthenticationToken)) {
                var claims = jwtHelper.getPayloadFromToken(token);
                AwesomeUserDetails userDetails = modelMapper.map(claims.get("user"), AwesomeUserDetails.class);
                userDetails.setToken(token);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authentication.setDetails(userDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

//}
