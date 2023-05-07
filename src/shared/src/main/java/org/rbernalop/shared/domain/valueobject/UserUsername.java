package org.rbernalop.shared.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.exception.InvalidUserDataException;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class UserUsername implements Serializable {
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