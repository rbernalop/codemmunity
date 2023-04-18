package org.rbernalop.apiauthentication.user.application.create;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiauthentication.shared.application.user.create.CreateUserCommand;
import org.rbernalop.apiauthentication.user.domain.port.CaptchaVerifier;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.value_object.*;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
@Slf4j
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {
    private final QueryBus queryBus;
    private final EventBus eventBus;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CaptchaVerifier captchaVerifier;

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

        UserCreator userCreator = new UserCreator(queryBus, eventBus, userRepository, passwordEncoder, captchaVerifier);
        userCreator.create(id, name, surname, username, email, password, birthDate, command.getCaptchaToken());
    }
}
