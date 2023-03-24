package org.rbernalop.apiscript.script.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ScriptPatchShareKeyControllerTest extends IntegrationTestCase {
    private static final String SCRIPTS_URL = "/api/v1/script";

    @Autowired
    private ScriptRepository scriptRepository;

    @AfterEach
    void tearDown() {
        scriptRepository.deleteAll();
    }

    @Test
    void should_regenerate_script_share_key() throws Exception {
        // GIVEN
        Script savedScript = ScriptMother.random();
        scriptRepository.save(savedScript);
        ScriptPatchShareKeyRequest request = ScriptPatchShareKeyRequestMother.random();

        // WHEN
        assertNotNull(savedScript.getId());
        assertRequestWithBody(
            HttpMethod.PATCH,
            SCRIPTS_URL + "/" + savedScript.getId().getValue() + "/shareKey",
            request,
            HttpStatus.OK
        );

        // THEN
        Optional<Script> optionalActualScript = scriptRepository.findById(savedScript.getId());
        assertTrue(optionalActualScript.isPresent());
        Script actualScript = optionalActualScript.get();
        assertEquals(request.getShareKey(), actualScript.getShareKey());
    }
}