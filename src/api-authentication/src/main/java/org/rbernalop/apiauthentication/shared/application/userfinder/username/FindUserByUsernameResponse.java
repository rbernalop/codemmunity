package org.rbernalop.apiauthentication.shared.application.userfinder.username;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindUserByUsernameResponse implements Response {
    private String id;
    private String username;
    private String email;
    private String password;
}
