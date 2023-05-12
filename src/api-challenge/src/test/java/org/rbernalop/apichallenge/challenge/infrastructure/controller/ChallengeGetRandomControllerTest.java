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
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomResponse;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomResponses;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeGetRandomControllerTest extends IntegrationTestCase {
    private static final String CHALLENGE_GET_RANDOM_PATH = "/api/v1/challenge/random";

    @Autowired
    private BaseCodeRepository baseCodeRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        challengeRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void should_get_random_challenges() throws Exception {
        // GIVEN
        Category category = ChallengeCategoryMother.random();
        categoryRepository.save(category);
        Challenge challenge = ChallengeMother.randomFromCategory(category);
        challengeRepository.save(challenge);
        BaseCode baseCode = BaseCodeMother.fromChallengeIdAndLanguageName(
            challenge.getId(), new LanguageName("Java")
        );
        baseCodeRepository.save(baseCode);

        // WHEN
        MvcResult mvcResult = assertRequest(
            HttpMethod.GET,
            CHALLENGE_GET_RANDOM_PATH + "?numChallenges=" + 1,
            HttpStatus.OK
        );

        // THEN
        ChallengeGetRandomResponses challengeGetRandomResponses = objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(),
            ChallengeGetRandomResponses.class
        );

        assertEquals(1, challengeGetRandomResponses.getChallenges().size());
        ChallengeGetRandomResponse challengeGetRandomResponse = challengeGetRandomResponses.getChallenges().get(0);
        assertNotNull(challenge.getId());
        assertEquals(challenge.getId().getValue(), challengeGetRandomResponse.getId());
        assertEquals(challenge.getTitle(), challengeGetRandomResponse.getTitle());
        assertEquals(challenge.getDescription(), challengeGetRandomResponse.getDescription());
        assertEquals(challenge.getCategory(), challengeGetRandomResponse.getCategory());
        assertEquals(challenge.getUserUsername(), challengeGetRandomResponse.getUserUsername());
        assertEquals(challenge.getDifficulty(), challengeGetRandomResponse.getDifficulty());
        assertTrue(challengeGetRandomResponse.getBaseCodes().size() > 0);
    }
}