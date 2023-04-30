package org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content;

import com.github.javafaker.Faker;

public class ChangeChallengeScriptContentCommandMother {
    public static ChangeChallengeScriptContentCommand random() {
        return new ChangeChallengeScriptContentCommand(
                Faker.instance().internet().uuid(),
                Faker.instance().name().username(),
                Faker.instance().lorem().paragraph()
        );
    }
}