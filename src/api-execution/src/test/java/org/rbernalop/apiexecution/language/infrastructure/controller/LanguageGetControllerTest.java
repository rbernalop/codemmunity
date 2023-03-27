package org.rbernalop.apiexecution.language.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apiexecution.language.domain.aggregate.LanguageMother;
import org.rbernalop.apiexecution.language.domain.repository.LanguageRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

class LanguageGetControllerTest extends IntegrationTestCase {
    private static final String BASE_URL = "/api/v1/language";

    @Autowired
    private LanguageRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void should_return_a_list_of_languages() throws Exception {
        // GIVEN
        var language = LanguageMother.random();
        repository.save(language);

        // WHEN
        MvcResult response = assertRequest(HttpMethod.GET, BASE_URL, HttpStatus.OK);

        // THEN
        LanguageGetControllerResponse expectedResponse =
            objectMapper.readValue(response.getResponse().getContentAsString(), LanguageGetControllerResponse.class);

        assertEquals(1, expectedResponse.getLanguages().size());
        LanguageGetResponse languageResponse = expectedResponse.getLanguages().get(0);
        assertNotNull(language.getId());
        assertEquals(language.getId().getValue(), languageResponse.getId());
        assertEquals(language.getName(), languageResponse.getName());
    }
}