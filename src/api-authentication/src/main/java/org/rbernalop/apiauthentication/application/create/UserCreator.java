package org.rbernalop.apiauthentication.application.create;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.domain.aggregate.User;
import org.rbernalop.apiauthentication.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.domain.port.UserRepository;
import org.rbernalop.apiauthentication.domain.service.UserSearcher;
import org.rbernalop.apiauthentication.domain.value_object.*;

@AllArgsConstructor
public class UserCreator {

    private UserRepository userRepository;

    public void create(UserId id, UserName name, UserSurname surname, UserUsername username, UserEmail email,
       UserPassword password, UserBirthDate birthDate) {
        UserSearcher userSearcher = new UserSearcher(userRepository);

        if (userSearcher.findById(id) != null) {
            throw new UserAlreadyExistsException("User with id " + id.getId() + " already exists");
        }

        if (userSearcher.findByUsername(username) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        if (userSearcher.findByEmail(email) != null) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User(id, name, surname, username, email, password, birthDate);
        userRepository.save(user);
    }
}
