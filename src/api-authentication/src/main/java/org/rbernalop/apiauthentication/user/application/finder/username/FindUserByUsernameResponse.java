package org.rbernalop.apiauthentication.user.application.finder.username;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindUserByUsernameResponse implements Response {
    private String id;
    private String username;
    private String email;
    private String password;
}
