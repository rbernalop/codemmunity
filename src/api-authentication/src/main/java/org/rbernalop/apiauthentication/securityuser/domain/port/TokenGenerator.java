package org.rbernalop.apiauthentication.securityuser.domain.port;

public interface TokenGenerator {
    String generateToken(String username, String password);
}
