package org.rbernalop.apiscript.script.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ScriptGetByUsernameControllerTest extends IntegrationTestCase {

    public static final String SCRIPT_GET_BY_USER_ID_ENDPOINT = "/api/v1/script";

    @Autowired
    private ScriptRepository scriptRepository;

    @AfterEach
    void tearDown() {
        scriptRepository.deleteAll();
    }

    @Test
    void should_return_a_list_of_scripts() throws UnsupportedEncodingException, JsonProcessingException {
        // GIVEN
        Script script = ScriptMother.random();
        scriptRepository.save(script);
        ScriptsGetByUsernameRequest request = ScriptsGetByUsernameRequestMother.randomForUsername(script.getOwnerName());

        // WHEN
        MvcResult result =
            assertDoesNotThrow(() -> assertRequest(
                HttpMethod.GET,
                SCRIPT_GET_BY_USER_ID_ENDPOINT + "?page=" + request.getPage() + "&size=" + request.getSize() + "&user=" + request.getUsername(),
                HttpStatus.OK));

        // THEN
        ScriptsGetByUsernameResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ScriptsGetByUsernameResponse.class);
        assertEquals(1, response.getScriptsResponses().size());
        assertNotNull(script.getId());
        assertEquals(script.getId().getValue(), response.getScriptsResponses().get(0).getId());
    }

    @Test
    void should_return_bad_request_when_page_is_negative() {
        // GIVEN
        ScriptsGetByUsernameRequest request = ScriptsGetByUsernameRequestMother.withNegativePage();

        // WHEN
        assertDoesNotThrow(() -> assertRequest(
            HttpMethod.GET,
            SCRIPT_GET_BY_USER_ID_ENDPOINT + "?page=" + request.getPage() + "&size=" + request.getSize() + "&user=" + request.getUsername(),
            HttpStatus.BAD_REQUEST));

        // THEN
    }



}