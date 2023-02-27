package org.rbernalop.apiauthentication.user.domain.value_object;


import lombok.NoArgsConstructor;
import org.rbernalop.apiauthentication.user.domain.exception.InvalidUserDataException;

@NoArgsConstructor
public class UserName {
    private String name;

    public UserName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidUserDataException("User name cannot be empty");
        }
        this.name = name;
    }

    
    public String getValue() {
        return name;
    }
}
