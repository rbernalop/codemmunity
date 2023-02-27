package org.rbernalop.apiauthentication.user.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.value_object.UserEmail;
import org.rbernalop.apiauthentication.user.domain.value_object.UserId;
import org.rbernalop.apiauthentication.user.domain.value_object.UserUsername;

@AllArgsConstructor
public class DomainUserSearcher {
    private final UserRepository userRepository;

    public User findById(UserId id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(UserUsername username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findByEmail(UserEmail email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
