package org.rbernalop.apiscript.script.infrastructure.controller;

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

import static org.junit.jupiter.api.Assertions.*;

class ScriptGetByIdControllerTest extends IntegrationTestCase {
    private static final String SCRIPTS_URL = "/api/v1/script";
    @Autowired
    private ScriptRepository scriptRepository;

    @AfterEach
    void tearDown() {
        scriptRepository.deleteAll();
    }

    @Test
    void should_return_script() throws Exception {
        // GIVEN
        Script savedScript = ScriptMother.random();
        scriptRepository.save(savedScript);
        assertNotNull(savedScript.getId());
        String scriptId = savedScript.getId().getValue();

        // WHEN
        MvcResult result = assertRequest(
            HttpMethod.GET,
            SCRIPTS_URL + "/" + scriptId,
            HttpStatus.OK
        );

        // THEN
        ScriptGetByIdResponse response =
            objectMapper.readValue(result.getResponse().getContentAsString(), ScriptGetByIdResponse.class);
        assertEquals(savedScript.getId().getValue(), response.getId());
        assertEquals(savedScript.getName(), response.getName());
        assertEquals(savedScript.getShareKey(), response.getShareKey());
        assertEquals(savedScript.getLanguageId(), response.getLanguageId());
    }

}