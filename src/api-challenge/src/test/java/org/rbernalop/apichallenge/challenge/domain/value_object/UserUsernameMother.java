package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class UserUsernameMother {

    public static UserUsername random() {
        return new UserUsername(Faker.instance().name().username());
    }
}