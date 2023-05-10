package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.apichallenge.challenge.domain.entity.ChallengeCategoryMother;
import org.rbernalop.apichallenge.challenge.domain.port.CategoryRepository;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChallengeGetRandomControllerTest extends IntegrationTestCase {
    private static final String CHALLENGE_GET_RANDOM_PATH = "/api/v1/challenge/random";

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

        // THEN
        assertEquals(1, challengeGetRandomResponses.getChallenges().size());
        ChallengeGetRandomResponse challengeGetRandomResponse = challengeGetRandomResponses.getChallenges().get(0);
        assertNotNull(challenge.getId());
        assertEquals(challenge.getId().getValue(), challengeGetRandomResponse.getId());
        assertEquals(challenge.getTitle(), challengeGetRandomResponse.getTitle());
        assertEquals(challenge.getDescription(), challengeGetRandomResponse.getDescription());
        assertEquals(challenge.getCategory(), challengeGetRandomResponse.getCategory());
        assertEquals(challenge.getUserUsername(), challengeGetRandomResponse.getUserUsername());
        assertEquals(challenge.getDifficulty(), challengeGetRandomResponse.getDifficulty());
    }
}