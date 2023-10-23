package osc.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtHelper {
    private final static String SECRET_KEY = "secret_key";
    private final long expiration = 5 * 60 * 60 * 60;

    public String generateToken(String email) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, this.getPublicKey())
                .compact();
    }

//    public String generateRefreshToken(String email) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 60))
//                .signWith(SignatureAlgorithm.HS512, this.getPublicKey())
//                .compact();
//    }
public String generateRefreshToken(String email,String userId,List <String> roles) throws NoSuchAlgorithmException, InvalidKeySpecException {
    return Jwts.builder()
            .claim("email", email)
            .claim("userId", userId)
            .claim ("roles",roles)
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration*60))
            .signWith(SignatureAlgorithm.HS512, this.getPublicKey())
            .compact();
}

    public String getSubject(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parser()
                .setSigningKey(this.getPublicKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.getPublicKey())
                    .parseClaimsJws(token);
            //Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
            return true;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, this.getPublicKey()).compact();
    }

    public Claims getUserIdFromToken(String token) {
        Claims result = null;
        try {
            result = Jwts.parser()
                    .setSigningKey(this.getPublicKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private String getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return SECRET_KEY;
    }
    public static void main (String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        List<String> roles = Arrays.asList("ADMIN","CUSTOMER","VENDOR");
        String email = "bwalyagad@gmail.com";
        String userId = "001";
        String token = new JwtHelper ().generateRefreshToken (email,userId,roles);
        System.out.println (token);
    }
}
