package org.rbernalop.servicesynchronization.shared.application.script.create;

import com.github.javafaker.Faker;

public class CreateScriptCommandMother {
    public static CreateScriptCommand random() {
        return new CreateScriptCommand(Faker.instance().internet().uuid());
    }
}