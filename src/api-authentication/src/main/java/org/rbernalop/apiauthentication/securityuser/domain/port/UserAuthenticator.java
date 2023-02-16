package org.rbernalop.apiauthentication.securityuser.domain.port;

public interface UserAuthenticator {
    void authenticate(String username, String password);
}
