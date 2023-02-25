package org.rbernalop.apiauthentication.healthcheck.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckGetControllerTest extends IntegrationTestCase {
    public static final String HEALTH_CHECK_GET_ENDPOINT = "/health-check";

    @Test
    void should_return_ok_status() throws UnsupportedEncodingException {
        // WHEN
        MvcResult result = assertDoesNotThrow(() -> assertRequest(
            HttpMethod.GET,
            HEALTH_CHECK_GET_ENDPOINT,
            HttpStatus.OK));

        // THEN
        assertEquals("{\"status\":\"ok\"}", result.getResponse().getContentAsString());
    }

}