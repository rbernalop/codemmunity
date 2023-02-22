package org.rbernalop.apiauthentication.securityuser.infrastructure.controller;

import com.github.javafaker.Faker;

public class LoginRequestMother {
    private final static Faker faker = new Faker();

    public static LoginRequest random() {
        return new LoginRequest(faker.name().username(), faker.internet().password());
    }
}
