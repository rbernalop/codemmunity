package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;

public class ScriptsGetByUsernameRequestMother {
    private final static Faker faker = new Faker();

    public static ScriptsGetByUsernameRequest random() {
        return new ScriptsGetByUsernameRequest(
            0,
            faker.number().numberBetween(1, 50),
            faker.internet().uuid()
        );
    }

    public static ScriptsGetByUsernameRequest randomForUsername(String username) {
        return new ScriptsGetByUsernameRequest(
            0,
            faker.number().numberBetween(1, 50),
            username
        );
    }

    public static ScriptsGetByUsernameRequest withNegativePage() {
        return new ScriptsGetByUsernameRequest(
            faker.number().numberBetween(-100, -1),
            faker.number().numberBetween(1, 50),
            faker.internet().uuid()
        );
    }
}
