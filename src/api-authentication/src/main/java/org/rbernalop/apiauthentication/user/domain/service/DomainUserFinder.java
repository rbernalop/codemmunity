package org.rbernalop.apiauthentication.user.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.exception.UserNotFoundException;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@AllArgsConstructor
public class DomainUserFinder {
    private final UserRepository userRepository;

    public User findByUsername(UserUsername username) {
        return userRepository.findByUsername(username).orElseThrow(
            () -> new UserNotFoundException("User '" + username.getValue() + "' does not exist")
        );
    }
}
