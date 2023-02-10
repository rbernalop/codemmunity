package org.rbernalop.apiauthentication.application.create;

import org.rbernalop.apiauthentication.domain.aggregate.User;
import org.rbernalop.apiauthentication.domain.value_object.*;

public class UserMother {

    public static User fromCommand(CreateUserCommand command) {
        return new User(
            new UserId(command.getId()),
            new UserName(command.getName()),
            new UserSurname(command.getSurname()),
            new UserUsername(command.getUsername()),
            new UserEmail(command.getEmail()),
            new UserPassword(command.getPassword()),
            new UserBirthDate(command.getBirthDate())
        );
    }

    public static User random() {
        return fromCommand(CreateUserCommandMother.random());
    }
}
