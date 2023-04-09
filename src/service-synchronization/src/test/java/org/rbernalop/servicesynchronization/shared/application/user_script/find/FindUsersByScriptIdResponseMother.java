package org.rbernalop.servicesynchronization.shared.application.user_script.find;

import com.github.javafaker.Faker;

import java.util.List;

public class FindUsersByScriptIdResponseMother {
    public static FindUsersByScriptIdResponse random() {
        List<String> usernames = List.of(Faker.instance().name().username());
        return new FindUsersByScriptIdResponse(usernames);
    }
}