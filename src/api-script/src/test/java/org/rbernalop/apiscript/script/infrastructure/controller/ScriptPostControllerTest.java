package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ScriptPostControllerTest extends IntegrationTestCase {
    public static final String SCRIPT_POST_ENDPOINT = "/api/v1/script";

    @Autowired
    private ScriptRepository repository;

    private final static Faker faker = new Faker();

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void should_create_a_script() {
        // GIVEN
        ScriptPostRequest request = ScriptPostRequestMother.random();
        String ownerUsername = faker.name().username();

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_POST_ENDPOINT + "?user=" + ownerUsername,
            request,
            HttpStatus.CREATED));

        // THEN
        assertEquals(1, repository.count());
        Script actualScript = repository.findAll().get(0);
        assertEquals(ownerUsername, actualScript.getOwnerName());
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
        String ownerUsername = faker.name().username();

        // WHEN
        assertDoesNotThrow(() -> assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_POST_ENDPOINT + "?user=" + ownerUsername,
            request,
            HttpStatus.BAD_REQUEST));
    }

}