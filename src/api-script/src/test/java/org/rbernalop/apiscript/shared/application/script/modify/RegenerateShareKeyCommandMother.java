package org.rbernalop.apiscript.shared.application.script.modify;

import com.github.javafaker.Faker;

public class RegenerateShareKeyCommandMother {
    private static final Faker faker = new Faker();

    public static RegenerateShareKeyCommand randomFromScriptId(String id) {
        return new RegenerateShareKeyCommand(id, faker.internet().uuid());
    }
}