package org.rbernalop.apiscript.shared.application.script.find.by_id;

import com.github.javafaker.Faker;

public class FindScriptsByIdQueryMother {
    private static final Faker faker = new Faker();

    public static FindScriptsByIdQuery random() {
        return new FindScriptsByIdQuery(faker.internet().uuid());
    }
}