package org.rbernalop.servicesynchronization.script.infrasctructure.socket;

import com.github.javafaker.Faker;

public class UserJoinSocketRequestMother {
    public static UserJoinSocketRequest random() {
        return new UserJoinSocketRequest(Faker.instance().name().username());
    }
}