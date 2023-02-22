package org.rbernalop.apiauthentication.shared.application.securityuser.login;

import com.github.javafaker.Faker;

public class LoginQueryMother {
    private final static Faker faker = new Faker();
    public static LoginQuery random() {
        return new LoginQuery(
                faker.name().username(),
                faker.internet().password()
        );
    }
}
