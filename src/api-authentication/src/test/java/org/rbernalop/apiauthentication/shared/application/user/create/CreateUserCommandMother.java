package org.rbernalop.apiauthentication.shared.application.user.create;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

public class CreateUserCommandMother {
    private final static Faker faker = new Faker();

    private static CreateUserCommand getRandomCreateUserCommand() {
        return new CreateUserCommand(
                UUID.randomUUID().toString(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    public static CreateUserCommand random() {
        return getRandomCreateUserCommand();
    }

    public static CreateUserCommand randomUnderAge() {
        CreateUserCommand command = getRandomCreateUserCommand();
        command.setBirthDate(LocalDate.now().minusYears(17));
        return command;
    }

    public static CreateUserCommand randomWithBlankName() {
        CreateUserCommand command = getRandomCreateUserCommand();
        command.setName("");
        return command;
    }

    public static CreateUserCommand randomWithBlankSurname() {
        CreateUserCommand command = getRandomCreateUserCommand();
        command.setSurname("");
        return command;
    }

    public static CreateUserCommand randomWithBlankUsername() {
        CreateUserCommand command = getRandomCreateUserCommand();
        command.setUsername("");
        return command;
    }

    public static CreateUserCommand randomWithInvalidEmail() {
        CreateUserCommand command = getRandomCreateUserCommand();
        command.setEmail("invalidEmail");
        return command;
    }

    public static CreateUserCommand randomWithInvalidId() {
        CreateUserCommand command = getRandomCreateUserCommand();
        command.setId("invalidId");
        return command;
    }
}
