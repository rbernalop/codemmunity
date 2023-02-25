package org.rbernalop.apiauthentication.user.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiauthentication.user.domain.exception.InvalidUserDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class UserUsername {
    private String username;

    public UserUsername(String username) {
        if(username == null || username.isBlank()) {
            throw new InvalidUserDataException("User username cannot be empty");
        }
        this.username = username;
    }

    
    public String getValue() {
        return username;
    }
}