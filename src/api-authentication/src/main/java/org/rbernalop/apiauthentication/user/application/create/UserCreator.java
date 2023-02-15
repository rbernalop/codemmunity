package org.rbernalop.apiauthentication.user.application.create;

import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.service.DomainUserSearcher;
import org.rbernalop.apiauthentication.user.domain.value_object.*;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;

public class UserCreator extends UseCase {

    private final UserRepository userRepository;

    public UserCreator(QueryBus queryBus, UserRepository userRepository) {
        super(queryBus);
        this.userRepository = userRepository;
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

        User user = new User(id, name, surname, username, email, password, birthDate);
        userRepository.save(user);
    }
}
