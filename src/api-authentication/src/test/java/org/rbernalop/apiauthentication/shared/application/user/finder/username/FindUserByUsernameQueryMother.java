package org.rbernalop.apiauthentication.shared.application.user.finder.username;

public class FindUserByUsernameQueryMother {
    public static FindUserByUsernameQuery fromUsername(String username) {
        return new FindUserByUsernameQuery(username);
    }
}
