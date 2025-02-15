package end.dev.whatsap_clone.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

//@ConfigurationProperties(prefix = "jwt")


@Getter
@Setter
@Component
public class JwtUtils {

    private final String secretKey;
    private final long accessTokenDuration;
    private final long refreshTokenDuration;

    public JwtUtils(
            @Value("${jwt.secret.key}") String secretKey,
            @Value("${jwt.access.token.duration}") long accessTokenDuration,
            @Value("${jwt.refresh.token.duration}") long refreshTokenDuration
    ) {
        System.out.println(secretKey);
        this.secretKey = secretKey;
        this.accessTokenDuration = accessTokenDuration;
        this.refreshTokenDuration = refreshTokenDuration;
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder().setSigningKey(getSignInKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String createToken(String email, Long duration) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createAccessToken(String email) {
        return this.createToken(email, accessTokenDuration);
    }

    public String createRefreshToken(String token) {
        return this.createToken(token, refreshTokenDuration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return userDetails.getUsername().equals(email) && !isTokenExpired(token);
    }
/*
*    public String  createRefreshToken(String email, String token){
        final  HashMap<String, String> claim = new HashMap<>();
        claim.put("email",email);
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(token)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ refreshTokenDuration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }*/
}
