package org.rbernalop.apitest.shared.application.test.check;

import com.github.javafaker.Faker;

public class CheckScriptTestsQueryMother {
    public static CheckScriptTestsQuery fromLanguageName(String languageName) {
        return new CheckScriptTestsQuery(
            Faker.instance().internet().uuid(),
            Faker.instance().lorem().sentence(),
            languageName
        );
    }
}