package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScriptPostControllerTest extends IntegrationTestCase {
    public static final String SCRIPT_POST_ENDPOINT = "/api/v1/script";

    @Autowired
    private ScriptRepository repository;

    @MockBean
    private EventBus eventBus;

    private final static Faker faker = new Faker();

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void should_create_a_script() {
        // GIVEN
        ScriptPostRequest request = ScriptPostRequestMother.random();
        String userUsername = faker.name().username();

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_POST_ENDPOINT + "?user=" + userUsername,
            request,
            HttpStatus.CREATED));

        // THEN
        verify(eventBus, times(1)).publish(any());
        assertEquals(1, repository.count());
        Script actualScript = repository.findAll().get(0);
        assertEquals(userUsername, actualScript.getOwnerName());
        assertEquals("Untitled script", actualScript.getName());
        assertNotNull(actualScript.getId());
        assertEquals(request.getId(), actualScript.getId().getValue());
        assertEquals(request.getKey(), actualScript.getShareKey());
        assertEquals(request.getLanguageId(), actualScript.getLanguageId());
    }

    @Test
    void should_return_bad_request_when_script_id_is_empty() {
        // GIVEN
        ScriptPostRequest request = ScriptPostRequestMother.withEmptyId();
        String userUserna = faker.name().username();

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_POST_ENDPOINT + "?user=" + userUserna,
            request,
            HttpStatus.BAD_REQUEST));

        // THEN
        verify(eventBus, never()).publish(any());
    }

}