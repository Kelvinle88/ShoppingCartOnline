package osc.security;

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
        return (AwesomeUserDetails) getAuthentication().getPrincipal();
    }

    public String getUserId() {
        //String result = getUserDetails().getId();
        //return result;
        return getUserDetails().getId();
    }

    public boolean isAdmin() {
        return getUserDetails().isAdmin();
    }
    public String getToken() {
        return getUserDetails ().getToken ();
    }
}
