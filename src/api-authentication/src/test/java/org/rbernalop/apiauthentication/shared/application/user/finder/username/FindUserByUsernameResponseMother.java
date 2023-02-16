package org.rbernalop.apiauthentication.shared.application.user.finder.username;

import com.github.javafaker.Faker;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;

public class FindUserByUsernameResponseMother {
    private final static Faker faker = new Faker();

    public static FindUserByUsernameResponse fromUser(User user) {
        assert user.getId() != null;
        return new FindUserByUsernameResponse(user.getId().getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword());
    }

    public static FindUserByUsernameResponse fromCredentials(String username, String password) {
        return new FindUserByUsernameResponse(faker.internet().uuid(), username, faker.internet().emailAddress(), password);
    }
}
