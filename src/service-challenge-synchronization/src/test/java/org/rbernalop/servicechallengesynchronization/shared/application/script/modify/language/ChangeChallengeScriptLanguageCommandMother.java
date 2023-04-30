package org.rbernalop.servicechallengesynchronization.shared.application.script.modify.language;

import com.github.javafaker.Faker;

public class ChangeChallengeScriptLanguageCommandMother {
    public static ChangeChallengeScriptLanguageCommand random() {
        return new ChangeChallengeScriptLanguageCommand(
                Faker.instance().internet().uuid(),
                Faker.instance().name().username(),
                Faker.instance().name().name()
        );
    }
}