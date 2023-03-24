package org.rbernalop.apiscript.shared.application.script.find;

import com.github.javafaker.Faker;
import org.rbernalop.apiscript.shared.application.script.find.by_userid.FindScriptsByUserIdQuery;

public class FindScriptsByUserIdQueryMother {

    private final static Faker faker = new Faker();

    public static FindScriptsByUserIdQuery randomFromUserId(String userId) {
        return new FindScriptsByUserIdQuery(
            userId,
        0,
            faker.number().numberBetween(1, 50)
        );
    }

    public static FindScriptsByUserIdQuery withNegativePage() {
        return new FindScriptsByUserIdQuery(
            faker.internet().uuid(),
            faker.number().numberBetween(-100, -1),
            faker.number().numberBetween(1, 50)
        );
    }

    public static FindScriptsByUserIdQuery withNegativeSize() {
        return new FindScriptsByUserIdQuery(
            faker.internet().uuid(),
            0,
            faker.number().numberBetween(-100, -1)
        );
    }
}