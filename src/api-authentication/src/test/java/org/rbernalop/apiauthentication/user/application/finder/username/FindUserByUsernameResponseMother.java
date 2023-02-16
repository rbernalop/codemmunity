package org.rbernalop.apiauthentication.user.application.finder.username;

import org.rbernalop.apiauthentication.shared.application.userfinder.username.FindUserByUsernameResponse;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;

public class FindUserByUsernameResponseMother {
    public static FindUserByUsernameResponse fromUser(User user) {
        assert user.getId() != null;
        return new FindUserByUsernameResponse(user.getId().getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword());
    }
}
