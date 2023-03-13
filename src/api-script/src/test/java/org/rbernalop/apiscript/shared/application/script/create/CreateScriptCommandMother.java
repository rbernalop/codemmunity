package org.rbernalop.apiscript.shared.application.script.create;

import com.github.javafaker.Faker;

public class CreateScriptCommandMother {
    private final static Faker faker = new Faker();

    public static CreateScriptCommand random() {
        return new CreateScriptCommand(
            faker.internet().uuid(),
            faker.internet().uuid(),
            faker.internet().uuid(),
            faker.name().username()
        );
    }

}