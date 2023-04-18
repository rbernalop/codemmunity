package org.rbernalop.apiscript.script.infrastructure.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ScriptPatchControllerTest extends IntegrationTestCase {

    private static final String SCRIPTS_URL = "/api/v1/script";
    @Autowired
    private ScriptRepository scriptRepository;
    private final static Faker faker = new Faker();

    @AfterEach
    void tearDown() {
        scriptRepository.deleteAll();
    }

    @Test
    void should_rename_script() throws Exception {
        // GIVEN
        Script savedScript = ScriptMother.random();
        scriptRepository.save(savedScript);

        Script expectedScript = ScriptMother.fromScriptAndNewName(savedScript, faker.name().name());
        assertNotNull(expectedScript.getId());
        String id = expectedScript.getId().getValue();

        ScriptPatchRequest request = ScriptPatchRequestMother.fromName(expectedScript.getName());

        // WHEN
        assertRequestWithBody(
            HttpMethod.PATCH,
            SCRIPTS_URL + "/" + id + "?user=" + expectedScript.getOwnerName(),
            request,
            HttpStatus.OK
        );

        // THEN
        Optional<Script> optionalActualScript = scriptRepository.findById(expectedScript.getId());
        assertTrue(optionalActualScript.isPresent());
        Script actualScript = optionalActualScript.get();
        assertEquals(expectedScript.getName(), actualScript.getName());
        assertEquals(expectedScript.getOwnerName(), actualScript.getOwnerName());
    }

    @Test
    void should_return_not_found_when_script_does_not_exist() throws Exception {
        // GIVEN
        ScriptPatchRequest request = ScriptPatchRequestMother.fromName(faker.name().name());
        String id = faker.internet().uuid();

        // WHEN
        assertRequestWithBody(
            HttpMethod.PATCH,
            SCRIPTS_URL + "/" + id + "?user=" + faker.name().username(),
            request,
            HttpStatus.NOT_FOUND
        );
    }

    @Test
    void should_return_unauthorized_when_user_is_not_owner() throws Exception {
        // GIVEN
        Script savedScript = ScriptMother.random();
        scriptRepository.save(savedScript);

        Script expectedScript = ScriptMother.fromScriptAndNewName(savedScript, faker.name().name());
        assertNotNull(expectedScript.getId());
        String id = expectedScript.getId().getValue();

        ScriptPatchRequest request = ScriptPatchRequestMother.fromName(expectedScript.getName());

        // WHEN
        assertRequestWithBody(
            HttpMethod.PATCH,
            SCRIPTS_URL + "/" + id + "?user=" + faker.name().username(),
            request,
            HttpStatus.UNAUTHORIZED
        );
    }

}