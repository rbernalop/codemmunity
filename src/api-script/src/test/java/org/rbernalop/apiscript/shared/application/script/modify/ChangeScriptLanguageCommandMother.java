package org.rbernalop.apiscript.shared.application.script.modify;

import com.github.javafaker.Faker;

public class ChangeScriptLanguageCommandMother {
    private static final Faker faker = new Faker();

    public static ChangeScriptLanguageCommand randomFromScriptId(String id) {
        return new ChangeScriptLanguageCommand(id, faker.internet().uuid());
    }
}