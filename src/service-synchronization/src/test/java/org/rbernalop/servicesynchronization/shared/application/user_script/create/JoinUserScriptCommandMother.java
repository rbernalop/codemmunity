package org.rbernalop.servicesynchronization.shared.application.user_script.create;

import com.github.javafaker.Faker;

public class JoinUserScriptCommandMother {
    public static JoinUserScriptCommand random() {
        return new JoinUserScriptCommand(Faker.instance().name().username(), Faker.instance().internet().uuid());
    }
}
