package org.rbernalop.apitest.test.domain.value_object;

import com.github.javafaker.Faker;

public class TestIdMother {
    public static TestId random() {
        return new TestId(Faker.instance().internet().uuid());
    }
}
