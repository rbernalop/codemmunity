package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;

public class ScriptPatchShareKeyRequestMother {
    private static final Faker faker = new Faker();

    public static ScriptPatchShareKeyRequest random() {
        return new ScriptPatchShareKeyRequest(faker.internet().uuid());
    }
}
