package org.rbernalop.apiauthentication.user.domain.value_object;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserPasswordEncrypted {
    private String passwordEncrypted;

    public UserPasswordEncrypted(String value) {
        this.passwordEncrypted = value;
    }

    
    public String getValue() {
        return passwordEncrypted;
    }
}
