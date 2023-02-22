package org.rbernalop.apiauthentication.securityuser.domain.port;

import java.util.List;
import java.util.Map;

public interface TokenGenerator {
    String generateToken(String username, List<String> authorities, Map<String, Object> claims);
}
