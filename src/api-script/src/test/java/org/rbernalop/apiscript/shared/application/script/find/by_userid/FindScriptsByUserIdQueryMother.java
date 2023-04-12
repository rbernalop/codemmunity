package org.rbernalop.apiscript.shared.application.script.find.by_userid;

import com.github.javafaker.Faker;

public class FindScriptsByUserIdQueryMother {

    private final static Faker faker = new Faker();

    public static FindScriptsByUsernameQuery randomFromUserId(String userId) {
        return new FindScriptsByUsernameQuery(
            userId,
        0,
            faker.number().numberBetween(1, 50)
        );
    }

    public static FindScriptsByUsernameQuery withNegativePage() {
        return new FindScriptsByUsernameQuery(
            faker.internet().uuid(),
            faker.number().numberBetween(-100, -1),
            faker.number().numberBetween(1, 50)
        );
    }

    public static FindScriptsByUsernameQuery withNegativeSize() {
        return new FindScriptsByUsernameQuery(
            faker.internet().uuid(),
            0,
            faker.number().numberBetween(-100, -1)
        );
    }
}