package org.rbernalop.apiscript.script.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScriptGetByOwnerUsernameControllerTest extends IntegrationTestCase {

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
        ScriptsGetByUserIdRequest request = ScriptsGetByUserIdRequestMother.randomForUserId(script.getOwnerName());

        // WHEN
        MvcResult result =
            assertDoesNotThrow(() -> assertRequest(
                HttpMethod.GET,
                SCRIPT_GET_BY_USER_ID_ENDPOINT + "?page=" + request.getPage() + "&size=" + request.getSize() + "&user=" + request.getOwnerId(),
                HttpStatus.OK));

        // THEN
        ScriptsGetByUserIdResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), ScriptsGetByUserIdResponse.class);
        assertEquals(1, response.getScriptsResponses().size());
        assertEquals(script.getId(), response.getScriptsResponses().get(0).getId());
    }

    @Test
    void should_return_bad_request_when_page_is_negative() {
        // GIVEN
        ScriptsGetByUserIdRequest request = ScriptsGetByUserIdRequestMother.withNegativePage();

        // WHEN
        assertDoesNotThrow(() -> assertRequest(
            HttpMethod.GET,
            SCRIPT_GET_BY_USER_ID_ENDPOINT + "?page=" + request.getPage() + "&size=" + request.getSize() + "&user=" + request.getOwnerId(),
            HttpStatus.BAD_REQUEST));

        // THEN
    }



}