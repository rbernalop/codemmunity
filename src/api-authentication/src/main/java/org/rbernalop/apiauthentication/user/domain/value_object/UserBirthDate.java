package org.rbernalop.apiauthentication.user.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.exception.InvalidUserDataException;

import java.time.LocalDate;

@NoArgsConstructor
public class UserBirthDate {
    private LocalDate birthDate;

    public UserBirthDate(LocalDate birthDate) {
        if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidUserDataException("User must be over 18 years old");
        }
        this.birthDate = birthDate;
    }

    
    public LocalDate getValue() {
        return birthDate;
    }
}
