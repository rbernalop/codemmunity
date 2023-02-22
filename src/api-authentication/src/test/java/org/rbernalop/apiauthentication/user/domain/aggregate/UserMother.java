package org.rbernalop.apiauthentication.user.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.apiauthentication.shared.application.user.create.CreateUserCommand;
import org.rbernalop.apiauthentication.shared.application.user.create.CreateUserCommandMother;
import org.rbernalop.apiauthentication.user.domain.value_object.*;

import java.time.ZoneId;

public class UserMother {

    private final static Faker faker = new Faker();
    public static User fromCommand(CreateUserCommand command) {
        return new User(
            new UserId(command.getId()),
            new UserName(command.getName()),
            new UserSurname(command.getSurname()),
            new UserUsername(command.getUsername()),
            new UserEmail(command.getEmail()),
            new UserPasswordEncrypted(command.getPassword()),
            new UserBirthDate(command.getBirthDate())
        );
    }

    public static User random() {
        return fromCommand(CreateUserCommandMother.random());
    }

    public static User fromCredentials(String username, String password) {
        return new User(
            new UserId(faker.internet().uuid()),
            new UserName(faker.name().firstName()),
            new UserSurname(faker.name().lastName()),
            new UserUsername(username),
            new UserEmail(faker.internet().emailAddress()),
            new UserPasswordEncrypted(password),
            new UserBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
        );
    }
}
