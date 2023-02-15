package org.rbernalop.apiauthentication.user.application.finder.username;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindUserByUsernameQuery implements Query {
    private String username;
}
