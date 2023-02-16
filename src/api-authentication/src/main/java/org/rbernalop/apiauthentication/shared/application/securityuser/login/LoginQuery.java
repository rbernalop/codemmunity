package org.rbernalop.apiauthentication.shared.application.securityuser.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginQuery implements Query {
    private String username;
    private String password;
}
