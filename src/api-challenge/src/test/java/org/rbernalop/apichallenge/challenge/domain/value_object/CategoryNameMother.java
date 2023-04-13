package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;

public class CategoryNameMother {

    public static CategoryName random() {
        return new CategoryName(Faker.instance().lorem().word());
    }
}