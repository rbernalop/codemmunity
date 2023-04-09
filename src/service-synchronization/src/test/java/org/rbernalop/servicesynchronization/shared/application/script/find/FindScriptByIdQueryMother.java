package org.rbernalop.servicesynchronization.shared.application.script.find;

import com.github.javafaker.Faker;

public class FindScriptByIdQueryMother {

        public static FindScriptByIdQuery random() {
            return new FindScriptByIdQuery(Faker.instance().internet().uuid());
        }
}