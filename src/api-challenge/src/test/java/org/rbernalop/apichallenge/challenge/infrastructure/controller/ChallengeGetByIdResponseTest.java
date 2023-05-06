package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCodeMother;
import org.rbernalop.apichallenge.baseCode.domain.port.BaseCodeRepository;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.apichallenge.challenge.domain.entity.ChallengeCategoryMother;
import org.rbernalop.apichallenge.challenge.domain.port.CategoryRepository;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeIdMother;
import org.rbernalop.apichallenge.completion.domain.value_object.LanguageNameMother;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChallengeGetByIdResponseTest extends IntegrationTestCase {
    private static final String CHALLENGE_GET_BY_ID_URL = "/api/v1/challenge/";

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BaseCodeRepository baseCodeRepository;

    @AfterEach
    void tearDown() {
        challengeRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void should_get_challenge_by_id() throws Exception {
        // GIVEN
        Category category = categoryRepository.save(ChallengeCategoryMother.random());
        Challenge challenge = ChallengeMother.randomFromCategory(category);
        LanguageName languageName = LanguageNameMother.random();
        challengeRepository.save(challenge);

        BaseCode baseCode = BaseCodeMother.fromChallengeIdAndLanguageName(challenge.getId(), languageName);
        baseCodeRepository.save(baseCode);

        // WHEN
        assertNotNull(challenge.getId());
        MvcResult mvcResult = assertRequest(
            HttpMethod.GET,
            CHALLENGE_GET_BY_ID_URL + challenge.getId().getValue() + "?languageName=" + languageName.getValue(),
            HttpStatus.OK
        );

        // THEN
        ChallengeGetByIdResponse challengeGetByIdResponse = objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), ChallengeGetByIdResponse.class);
        assertEquals(challenge.getId().getValue(), challengeGetByIdResponse.getId());
        assertEquals(challenge.getTitle(), challengeGetByIdResponse.getTitle());
        assertEquals(challenge.getDescription(), challengeGetByIdResponse.getDescription());
        assertEquals(challenge.getCategory(), challengeGetByIdResponse.getCategory());
        assertEquals(challenge.getDifficulty(), challengeGetByIdResponse.getDifficulty());
        assertEquals(challenge.getUserUsername(), challengeGetByIdResponse.getCreatorUsername());
        assertEquals(baseCode.getContent(), challengeGetByIdResponse.getBaseScript());
    }

    @Test
    void should_return_not_found_when_challenge_does_not_exist() throws Exception {
        // GIVEN
        String challengeId = ChallengeIdMother.random().getValue();
        LanguageName languageName = LanguageNameMother.random();

        // WHEN
        assertRequest(
            HttpMethod.GET,
            CHALLENGE_GET_BY_ID_URL + challengeId + "?languageName=" + languageName.getValue(),
            HttpStatus.NOT_FOUND
        );

        // THEN
    }
}