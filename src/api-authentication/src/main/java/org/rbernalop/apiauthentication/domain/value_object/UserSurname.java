package org.rbernalop.apiauthentication.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.apiauthentication.domain.exception.InvalidUserDataException;

@NoArgsConstructor
public class UserSurname {
    private String surname;

    public UserSurname(String surname) {
        if(surname == null || surname.isBlank()) {
            throw new InvalidUserDataException("User name cannot be empty");
        }
        this.surname = surname;
    }

    
    public String getValue() {
        return surname;
    }
}

