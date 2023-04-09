package org.rbernalop.servicesynchronization.shared.application.script.find;

import com.github.javafaker.Faker;

import java.util.List;

public class FindScriptByIdResponseMother {
        public static FindScriptByIdResponse random() {
            return new FindScriptByIdResponse(
                Faker.instance().lorem().sentence(),
                List.of(Faker.instance().name().username())
            );
        }
}