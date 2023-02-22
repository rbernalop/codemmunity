package org.rbernalop.apiauthentication.user.application.create;

import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.service.DomainUserSearcher;
import org.rbernalop.apiauthentication.user.domain.value_object.*;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCreator extends UseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserCreator(QueryBus queryBus, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(queryBus);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserId id, UserName name, UserSurname surname, UserUsername username, UserEmail email,
                       UserPassword password, UserBirthDate birthDate) {
        DomainUserSearcher domainUserSearcher = new DomainUserSearcher(userRepository);

        if (domainUserSearcher.findById(id) != null) {
            throw new UserAlreadyExistsException("User with id " + id.getId() + " already exists");
        }

        if (domainUserSearcher.findByUsername(username) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        if (domainUserSearcher.findByEmail(email) != null) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        UserPasswordEncrypted passwordEncrypted = new UserPasswordEncrypted(passwordEncoder.encode(password.getValue()));
        User user = new User(id, name, surname, username, email, passwordEncrypted, birthDate);
        userRepository.save(user);
    }
}
