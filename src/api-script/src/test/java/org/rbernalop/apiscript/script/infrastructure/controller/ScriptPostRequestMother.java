package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;

public class ScriptPostRequestMother {
    private final static Faker faker = new Faker();

    public static ScriptPostRequest random() {
        return new ScriptPostRequest(
            faker.internet().uuid(),
            faker.internet().uuid(),
            faker.internet().uuid()
        );
    }

    public static ScriptPostRequest withEmptyId() {
        return new ScriptPostRequest(
            "",
            faker.internet().uuid(),
            faker.internet().uuid()
        );
    }
}
