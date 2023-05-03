package org.rbernalop.apitest.test.domain.value_object;

import com.github.javafaker.Faker;

public class TestNameMother {

    public static TestName random() {
        return new TestName(Faker.instance().name().fullName());
    }
}
