package org.rbernalop.apiauthentication.domain.value_object;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiauthentication.domain.exception.InvalidUserDataException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@NoArgsConstructor
@EqualsAndHashCode
public class UserEmail {
    private String email;
    public UserEmail(String email) {
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidUserDataException("Invalid email");
        }
        this.email = email;
    }

    
    public String getValue() {
        return email;
    }
}
