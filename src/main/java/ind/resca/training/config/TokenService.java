package ind.resca.training.config;

import ind.resca.training.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService{

    private final String secretKey = "66gNjtlvfsdgp3zAqaLMw5nJwwAwCD0Yy4cJIFKg5Cp3m2wgfGNZmF4r0jRke8qqr2n2oZ8fFgVC7sw7";

    public String genToken(Authentication auth) {
        User user = (User) auth.getPrincipal();

        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + 86400000);

        return Jwts.builder()
                .setIssuer("API da Pet Shop Scenario")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UUID getUserId(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        return UUID.fromString(claims.getSubject());
    }
}
