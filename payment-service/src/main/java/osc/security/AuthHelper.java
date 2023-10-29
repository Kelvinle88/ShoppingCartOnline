package osc.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import osc.enums.Role;

@Component
public class AuthHelper {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getName() {
        return getAuthentication().getName();
    }

    public AwesomeUserDetails getUserDetails() {
        if (getAuthentication() instanceof AnonymousAuthenticationToken) {
            return new AwesomeUserDetails();
        }
        return (AwesomeUserDetails) getAuthentication().getPrincipal();
    }

    public Integer getUserId() {
        return getUserDetails().getUserId();
    }

    public String getEmail() {
        return getUserDetails().getUsername();
    }

    public boolean isAdmin() {
        return getUserDetails().isAdmin();
    }

    public Role getRole() {
        return getUserDetails().getRole();
    }

    public String getToken() {
        return getUserDetails().getToken();
    }
}
