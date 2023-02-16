package org.rbernalop.apiauthentication.user.application.finder.username;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindUserByUsernameQuery implements Query {
    private String username;
}
