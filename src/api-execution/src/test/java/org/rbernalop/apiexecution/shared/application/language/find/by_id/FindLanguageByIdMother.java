package org.rbernalop.apiexecution.shared.application.language.find.by_id;

import com.github.javafaker.Faker;

public class FindLanguageByIdMother {

    private static final Faker faker = new Faker();

    public static FindLanguageByIdQuery fromLanguageId(String id) {
        return new FindLanguageByIdQuery(id);
    }

    public static FindLanguageByIdQuery random() {
        return new FindLanguageByIdQuery(faker.internet().uuid());
    }
}