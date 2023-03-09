package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;

public class ScriptsGetByUserIdRequestMother {
    private final static Faker faker = new Faker();

    public static ScriptsGetByUserIdRequest random() {
        return new ScriptsGetByUserIdRequest(
            0,
            faker.number().numberBetween(1, 50),
            faker.internet().uuid()
        );
    }

    public static ScriptsGetByUserIdRequest randomForUserId(String ownerId) {
        return new ScriptsGetByUserIdRequest(
            0,
            faker.number().numberBetween(1, 50),
            ownerId
        );
    }

    public static ScriptsGetByUserIdRequest withNegativePage() {
        return new ScriptsGetByUserIdRequest(
            faker.number().numberBetween(-100, -1),
            faker.number().numberBetween(1, 50),
            faker.internet().uuid()
        );
    }
}
