package osc.productservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthHelper {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getName() {
        return getAuthentication().getName();
    }

    public AwesomeUserDetails getUserDetails() {
        System.out.println (getAuthentication().getPrincipal().toString ());
        return (AwesomeUserDetails) getAuthentication().getPrincipal();
    }

    public String getUserId() {
        String result = getUserDetails().getId();
        return result;
        //return getUserDetails().getId();
    }

    public boolean isAdmin() {
        return getUserDetails().isAdmin();
    }
}
