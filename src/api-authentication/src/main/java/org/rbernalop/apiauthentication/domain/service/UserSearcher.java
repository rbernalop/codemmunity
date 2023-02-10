package org.rbernalop.apiauthentication.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.domain.aggregate.User;
import org.rbernalop.apiauthentication.domain.port.UserRepository;
import org.rbernalop.apiauthentication.domain.value_object.UserEmail;
import org.rbernalop.apiauthentication.domain.value_object.UserId;
import org.rbernalop.apiauthentication.domain.value_object.UserUsername;

@AllArgsConstructor
public class UserSearcher {
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
