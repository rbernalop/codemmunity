package org.rbernalop.servicesynchronization.shared.application.user_script.find;

import com.github.javafaker.Faker;

public class FindUsersByScriptIdQueryMother {
    public static FindUsersByScriptIdQuery random() {
        return new FindUsersByScriptIdQuery(Faker.instance().internet().uuid());
    }
}