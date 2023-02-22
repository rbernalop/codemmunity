package org.rbernalop.apiauthentication.user.infrastructure.controller;

import com.github.javafaker.Faker;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;

import java.time.ZoneId;

public class UserPostRequestMother {
    public static UserPostRequest random() {
        Faker faker = new Faker();
        UserPostRequest request = new UserPostRequest();
        request.setId(faker.internet().uuid());
        request.setName(faker.name().firstName());
        request.setSurname(faker.name().lastName());
        request.setUsername(faker.name().username());
        request.setEmail(faker.internet().emailAddress());
        request.setPassword(faker.internet().password());
        request.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return request;
    }

    public static UserPostRequest randomWithEmptyName() {
        UserPostRequest request = random();
        request.setName("");
        return request;
    }

    public static UserPostRequest fromUser(User user) {
        UserPostRequest request = new UserPostRequest();
        assert user.getId() != null;
        request.setId(user.getId().getId());
        request.setName(user.getName());
        request.setSurname(user.getSurname());
        request.setUsername(user.getUsername());
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword());
        request.setBirthDate(user.getBirthDate());
        return request;
    }
}
