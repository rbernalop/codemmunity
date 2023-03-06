package org.rbernalop.apigateway.security.infrastructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.rbernalop.shared.domain.SecurityUser;

public class JwtTokenManager {

    public static SecurityUser getSecurityUserFromBearerToken(String token, String secretJwtKey) {
        try {
            if(token == null || token.isBlank() || !token.startsWith("Bearer ")) {
                return null;
            }
            token = token.replace("Bearer ", "");

            Algorithm algorithm = Algorithm.HMAC512(secretJwtKey);
            DecodedJWT decodedJwt = JWT.require(algorithm).build().verify(token);

            String username = decodedJwt.getSubject();

            return new SecurityUser(username);
        } catch (Exception e) {
            return null;
        }
    }
}