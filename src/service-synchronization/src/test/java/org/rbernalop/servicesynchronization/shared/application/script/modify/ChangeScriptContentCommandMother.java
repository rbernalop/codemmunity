package org.rbernalop.servicesynchronization.shared.application.script.modify;

import com.github.javafaker.Faker;

public class ChangeScriptContentCommandMother {
    public static ChangeScriptContentCommand random() {
        return new ChangeScriptContentCommand(Faker.instance().internet().uuid(), Faker.instance().lorem().sentence());
    }
}