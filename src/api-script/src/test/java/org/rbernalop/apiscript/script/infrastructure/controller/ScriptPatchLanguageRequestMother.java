package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;

public class ScriptPatchLanguageRequestMother {
    private static final Faker faker = new Faker();

    public static ScriptPatchLanguageRequest random() {
        return new ScriptPatchLanguageRequest(faker.internet().uuid());
    }
}
