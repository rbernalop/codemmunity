package org.rbernalop.apiauthentication.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.domain.aggregate.User;
import org.rbernalop.apiauthentication.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.domain.port.UserRepository;
import org.rbernalop.apiauthentication.domain.value_object.UserEmail;
import org.rbernalop.apiauthentication.domain.value_object.UserId;
import org.rbernalop.apiauthentication.domain.value_object.UserUsername;

@AllArgsConstructor
public class DomainUserFinder {
    private final UserRepository userRepository;

    public User findById(UserId id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserAlreadyExistsException("User with id " + id.getId() + " already exists")
        );
    }

    public User findByUsername(UserUsername username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserAlreadyExistsException("User already exists")
        );
    }

    public User findByEmail(UserEmail email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserAlreadyExistsException("Email already exists")
        );
    }
}
