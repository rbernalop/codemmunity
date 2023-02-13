package org.rbernalop.apiauthentication.application.create;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.domain.aggregate.User;
import org.rbernalop.apiauthentication.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.domain.port.UserRepository;
import org.rbernalop.apiauthentication.domain.service.DomainUserSearcher;
import org.rbernalop.apiauthentication.domain.value_object.*;

@AllArgsConstructor
public class UserCreator {

    private UserRepository userRepository;

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
