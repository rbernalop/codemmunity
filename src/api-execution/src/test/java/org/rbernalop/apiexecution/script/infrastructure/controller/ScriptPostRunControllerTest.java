package org.rbernalop.apiexecution.script.infrastructure.controller;

import org.junit.jupiter.api.*;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.language.domain.aggregate.LanguageMother;
import org.rbernalop.apiexecution.language.domain.repository.LanguageRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

class ScriptPostRunControllerTest extends IntegrationTestCase {
    private static final String SCRIPT_RUN_ENDPOINT = "/api/v1/script/run";

    @Autowired
    private LanguageRepository languageRepository;

    @AfterEach
    void tearDown() {
        languageRepository.deleteAll();
    }


    @Test
    void should_run_python_script() throws Exception {
        // GIVEN
        Language pythonLanguage = LanguageMother.fromName("Python");
        languageRepository.save(pythonLanguage);
        assertNotNull(pythonLanguage.getId());
        ScriptPostRunRequest request = ScriptPostRunRequestMother.pythonCode(pythonLanguage.getId().getValue());

        // WHEN
        MvcResult mvcResult = assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_RUN_ENDPOINT,
            request,
            HttpStatus.OK
        );

        // THEN
        ScriptPostRunResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ScriptPostRunResponse.class);
        assertNull(response.getCompilationOutput());
        assertEquals("Hello world\r\n", response.getExecutionOutput());
    }

    @Test
    void should_run_node_js_script() throws Exception {
        // GIVEN
        Language nodejsLanguage = LanguageMother.fromName("NodeJS");
        languageRepository.save(nodejsLanguage);
        assertNotNull(nodejsLanguage.getId());
        ScriptPostRunRequest request = ScriptPostRunRequestMother.nodeJsCode(nodejsLanguage.getId().getValue());

        // WHEN
        MvcResult mvcResult = assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_RUN_ENDPOINT,
            request,
            HttpStatus.OK
        );

        // THEN
        ScriptPostRunResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ScriptPostRunResponse.class);
        assertNull(response.getCompilationOutput());
        assertEquals("Hello world\n", response.getExecutionOutput());
    }

    @Test
    void should_run_java_script() throws Exception {
        // GIVEN
        Language javaLanguage = LanguageMother.fromName("Java");
        languageRepository.save(javaLanguage);
        assertNotNull(javaLanguage.getId());
        ScriptPostRunRequest request = ScriptPostRunRequestMother.javaCode(javaLanguage.getId().getValue());

        // WHEN
        MvcResult mvcResult = assertRequestWithBody(
            HttpMethod.POST,
            SCRIPT_RUN_ENDPOINT,
            request,
            HttpStatus.OK
        );

        // THEN
        ScriptPostRunResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ScriptPostRunResponse.class);
        assertNotNull(response.getCompilationOutput());
        assertEquals("Hello world\r\n", response.getExecutionOutput());
    }
}