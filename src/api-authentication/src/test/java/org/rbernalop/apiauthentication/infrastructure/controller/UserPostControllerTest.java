package org.rbernalop.apiauthentication.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiauthentication.application.create.UserMother;
import org.rbernalop.apiauthentication.domain.aggregate.User;
import org.rbernalop.apiauthentication.domain.port.UserRepository;
import org.rbernalop.apiauthentication.domain.value_object.UserId;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class UserPostControllerTest extends IntegrationTestCase {

    public static final String USER_POST_ENDPOINT = "/api/v1/user";
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void should_create_a_valid_user() {
        // GIVEN
        UserPostRequest request = UserPostRequestMother.random();
        UserId id = new UserId(request.getId());

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            USER_POST_ENDPOINT,
            request,
            HttpStatus.CREATED));

        // THEN
        assertTrue(userRepository.findById(id).isPresent());
    }

    @Test
    void should_return_bad_request_when_user_name_is_empty() {
        // GIVEN
        UserPostRequest request = UserPostRequestMother.randomWithEmptyName();

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            USER_POST_ENDPOINT,
            request,
            HttpStatus.BAD_REQUEST));

        // THEN
        assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    void should_return_conflict_when_user_already_exists() {
        // GIVEN
        User user = UserMother.random();
        userRepository.save(user);
        UserPostRequest request = UserPostRequestMother.fromUser(user);
        UserId id = new UserId(request.getId());

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            USER_POST_ENDPOINT,
            request,
            HttpStatus.CONFLICT));

        // THEN
        assertTrue(userRepository.findById(id).isPresent());
        assertEquals(1, userRepository.findAll().size());
    }
}