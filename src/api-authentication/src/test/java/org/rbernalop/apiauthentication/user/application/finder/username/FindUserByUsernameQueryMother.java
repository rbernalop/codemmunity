package org.rbernalop.apiauthentication.user.application.finder.username;

import org.rbernalop.apiauthentication.shared.application.userfinder.username.FindUserByUsernameQuery;

public class FindUserByUsernameQueryMother {
    public static FindUserByUsernameQuery fromUsername(String username) {
        return new FindUserByUsernameQuery(username);
    }
}
