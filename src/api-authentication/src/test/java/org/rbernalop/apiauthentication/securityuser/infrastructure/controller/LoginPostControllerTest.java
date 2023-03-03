package org.rbernalop.apiauthentication.securityuser.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.aggregate.UserMother;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class LoginPostControllerTest extends IntegrationTestCase {
    public static final String LOGIN_POST_ENDPOINT = "/api/v1/login";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void should_login_a_valid_user() throws UnsupportedEncodingException, JsonProcessingException {
        // GIVEN
        LoginRequest request = LoginRequestMother.random();
        User user = UserMother.fromCredentials(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        // WHEN
        MvcResult result = assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            LOGIN_POST_ENDPOINT,
            request,
            HttpStatus.OK));

        // THEN
        assertNotNull(result.getResponse().getHeader("Authorization"));
        LoginResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponse.class);
        assert user.getId() != null;
        assertEquals(user.getId().getValue(), response.getId());
        assertEquals(user.getUsername(), response.getUsername());
        assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    void should_return_forbidden_when_login_with_invalid_credentials() throws UnsupportedEncodingException {
        // GIVEN
        LoginRequest request = LoginRequestMother.random();

        // WHEN
        MvcResult result = assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            LOGIN_POST_ENDPOINT,
            request,
            HttpStatus.FORBIDDEN));

        // THEN
        assertNull(result.getResponse().getHeader("Authorization"));
        assertTrue(result.getResponse().getContentAsString().contains("Invalid credentials"));
    }
}