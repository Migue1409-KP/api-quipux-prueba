package co.quipux.pruebatecnica.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtility {

    @Value("${jwt.secret}")
    private String secret;

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiress(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimSolve) {
        final Claims claims = extractAllClaims(token);
        return claimSolve.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean tokenExpired(String token) {
        return extractExpiress(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails, UUID id, String rol) {
        return createToken(userDetails.getUsername(), id, rol);
    }

    private String createToken(String sujeto, UUID id, String rol) {
        return Jwts.builder()
                .setSubject(sujeto)
                .claim("id", id.toString())
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !tokenExpired(token));
    }
}
