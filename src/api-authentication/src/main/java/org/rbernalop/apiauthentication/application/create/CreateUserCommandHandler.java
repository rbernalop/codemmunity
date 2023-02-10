package org.rbernalop.apiauthentication.application.create;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiauthentication.domain.port.UserRepository;
import org.rbernalop.apiauthentication.domain.value_object.*;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;

@Service
@AllArgsConstructor
@Slf4j
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {
    private final UserRepository userRepository;

    @Override
    public void handle(CreateUserCommand command) {
        log.info("Creating user with id: {}, name: {}, surname: {}, username: {}, email: {}, birthDate: {}",
            command.getId(), command.getName(), command.getSurname(), command.getUsername(), command.getEmail(),
            command.getBirthDate());

        UserId id = new UserId(command.getId());
        UserName name = new UserName(command.getName());
        UserSurname surname = new UserSurname(command.getSurname());
        UserUsername username = new UserUsername(command.getUsername());
        UserEmail email = new UserEmail(command.getEmail());
        UserPassword password = new UserPassword(command.getPassword());
        UserBirthDate birthDate = new UserBirthDate(command.getBirthDate());

        UserCreator userCreator = new UserCreator(userRepository);
        userCreator.create(id, name, surname, username, email, password, birthDate);
    }
}
