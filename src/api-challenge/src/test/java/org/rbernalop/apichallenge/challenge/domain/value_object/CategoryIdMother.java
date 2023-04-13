package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;

public class CategoryIdMother {

    public static CategoryId random() {
        return new CategoryId(Faker.instance().internet().uuid());
    }
}
