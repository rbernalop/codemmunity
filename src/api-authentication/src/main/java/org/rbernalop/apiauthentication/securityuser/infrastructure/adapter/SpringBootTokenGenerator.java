package org.rbernalop.apiauthentication.securityuser.infrastructure.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.securityuser.domain.port.TokenGenerator;
import org.rbernalop.apiauthentication.securityuser.infrastructure.configuration.JwtConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class SpringBootTokenGenerator implements TokenGenerator {
    private final JwtConfiguration jwtConfiguration;

    @Override
    public String generateToken(String username, List<String> authorities, Map<String, Object> claims) {
        int tokenExpirationTime = jwtConfiguration.getExpiration();
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + tokenExpirationTime);
        String authoritiesSerialized = authorities != null ? String.join(",", authorities) : null;

        return JWT.create()
                .withSubject(username)
                .withClaim("authorities", authoritiesSerialized)
                .withClaim("claims", claims)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(this.jwtConfiguration.getKey()));
    }
}
