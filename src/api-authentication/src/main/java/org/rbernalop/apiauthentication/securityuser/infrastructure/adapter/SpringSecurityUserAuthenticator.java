package org.rbernalop.apiauthentication.securityuser.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.securityuser.domain.exception.InvalidCredentialsException;
import org.rbernalop.apiauthentication.securityuser.domain.port.UserAuthenticator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@AllArgsConstructor
public class SpringSecurityUserAuthenticator implements UserAuthenticator {
    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(String username, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
