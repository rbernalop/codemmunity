package org.rbernalop.apiauthentication.shared.application.securityuser.login;

import com.github.javafaker.Faker;

public class LoginQueryResponseMother {
    private final static Faker faker = new Faker();
    public static LoginQueryResponse fromUserData(String uuid, String username, String email) {
        return new LoginQueryResponse(faker.internet().password(), uuid, username, email);
    }
}
