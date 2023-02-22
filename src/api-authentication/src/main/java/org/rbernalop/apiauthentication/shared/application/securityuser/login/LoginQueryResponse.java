package org.rbernalop.apiauthentication.shared.application.securityuser.login;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LoginQueryResponse implements Response {
    private String token;
    private String id;
    private String username;
    private String email;
}
