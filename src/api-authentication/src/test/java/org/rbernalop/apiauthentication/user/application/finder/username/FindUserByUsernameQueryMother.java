package org.rbernalop.apiauthentication.user.application.finder.username;

public class FindUserByUsernameQueryMother {
    public static FindUserByUsernameQuery fromUsername(String username) {
        return new FindUserByUsernameQuery(username);
    }
}
