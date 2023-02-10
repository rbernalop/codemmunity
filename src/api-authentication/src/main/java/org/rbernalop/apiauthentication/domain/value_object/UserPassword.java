package org.rbernalop.apiauthentication.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserPassword {
    private String password;

    public UserPassword(String value) {
        this.password = value;
    }

    
    public String getValue() {
        return password;
    }
}
