package org.rbernalop.apitest.shared.application.test.send_results;

import com.github.javafaker.Faker;

public class SendTestsCompletionCommandMother {
    public static SendTestsCompletionCommand randomFromPassed(boolean passed) {
        return new SendTestsCompletionCommand(passed,
                Faker.instance().name().username(),
                Faker.instance().internet().uuid(),
                Faker.instance().lorem().paragraph(),
                Faker.instance().name().name());
    }
}