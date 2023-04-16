package org.rbernalop.apiauthentication.user.domain.aggregate;import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiauthentication.user.domain.value_object.*;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.UserId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends AggregateRoot {
    @EmbeddedId
    private UserId id;

    @Embedded
    private UserName name;

    @Embedded
    private UserSurname surname;

    @Embedded
    private UserUsername username;

    @Embedded
    private UserEmail email;

    @Embedded
    private UserPasswordEncrypted password;

    @Embedded
    private UserBirthDate birthDate;

    public User(UserId id, UserName name, UserSurname surname, UserUsername username, UserEmail email, UserPasswordEncrypted password, UserBirthDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    @Override
    public UserId getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }

    public String getSurname() {
        return surname.getValue();
    }

    public String getUsername() {
        return username.getValue();
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public LocalDate getBirthDate() {
        return birthDate.getValue();
    }
}
