//package osc.security;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import io.jsonwebtoken.Claims;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//public class AwesomeUserDetails implements UserDetails {
//
//    private String id;
//    private String email;
//    @JsonIgnore
//    private String password;
//    private List<String> roles;
//    private String token;
//    public AwesomeUserDetails(Claims claims) {
//        //this.id = claims.getSubject();
//        this.id = claims.get ("userId",String.class);
//        this.email = claims.get("email", String.class);
//        //Map <String, Object> realmAccess = (Map <String, Object>) claims.get("realm_access");
//        //this.roles = ((List<String>)realmAccess.get("roles"));
//        this.roles = ((List<String>)claims.get("roles"));
//        System.out.println(claims);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //return null;
//        return roles.stream()
//                .map(Object::toString)
//                .map(r -> "ROLE_" + r.toUpperCase())
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    public boolean isAdmin() {
//        return roles.contains("ADMIN");
//    }
//    public boolean isVendor() {
//        return roles.contains("VENDOR");
//    }
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
package osc.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import osc.enums.Role;
import osc.enums.UserStatus;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwesomeUserDetails implements UserDetails {

    private Integer userId;

    private String email;

    private UserStatus status;

    private Role role;

    private String token;

    public Role getRole() {
        return role;
    }

    @Override
    @JsonIgnore
    public Collection <? extends GrantedAuthority> getAuthorities() {
        var simpleGrantedAuthority = new SimpleGrantedAuthority (String.format("ROLE_%s", role.name().toUpperCase(Locale.ROOT)));
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == UserStatus.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == UserStatus.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == UserStatus.ACTIVE;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}