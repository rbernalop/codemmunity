package org.rbernalop.servicesynchronization.shared.application.user_script.delete;

import com.github.javafaker.Faker;

public class LeaveUserScriptCommandMother {
    public static LeaveUserScriptCommand random() {
        return new LeaveUserScriptCommand(Faker.instance().name().username(), Faker.instance().internet().uuid());
    }
}