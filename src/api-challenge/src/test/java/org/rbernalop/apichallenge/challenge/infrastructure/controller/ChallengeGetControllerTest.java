package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.apichallenge.challenge.domain.entity.ChallengeCategoryMother;
import org.rbernalop.apichallenge.challenge.domain.port.CategoryRepository;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.aggregate.CompletionMother;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeGetControllerTest extends IntegrationTestCase {
    private static final String GET_CHALLENGES_URL = "/api/v1/challenge";

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompletionRepository completionRepository;

    @AfterEach
    void tearDown() {
        challengeRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void should_get_all_challenges() throws Exception {
        // GIVEN
        Category category = categoryRepository.save(ChallengeCategoryMother.random());
        Challenge challenge = ChallengeMother.randomFromCategory(category);
        challengeRepository.save(challenge);
        assertNotNull(challenge.getId());
        Completion completion = CompletionMother.fromChallengeIdAndUsername(challenge.getId().getValue(), challenge.getUserUsername());
        completionRepository.save(completion);
        int page = 0;
        int size = 10;

        // WHEN
        MvcResult mvcResult = assertRequest(
            HttpMethod.GET,
        GET_CHALLENGES_URL + "?page=" + page + "&size=" + size + "&user=" + challenge.getUserUsername(),
            HttpStatus.OK
        );

        // THEN
        ChallengeGetResponses challengeGetResponses = objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), ChallengeGetResponses.class);
        assertEquals(1, challengeGetResponses.getChallenges().size());
        ChallengeGetResponse actualChallenge = challengeGetResponses.getChallenges().get(0);
        assertEquals(challenge.getId().getValue(), actualChallenge.getId());
        assertEquals(challenge.getTitle(), actualChallenge.getTitle());
        assertEquals(challenge.getCategory(), actualChallenge.getCategory());
        assertEquals(challenge.getDifficulty(), actualChallenge.getDifficulty());
        assertTrue(actualChallenge.isCompleted());
    }

    @Test
    void should_return_bad_request_when_page_or_size_are_negative() throws Exception {
        // GIVEN
        int page = -1;
        int size = -1;

        // WHEN
        assertRequest(
            HttpMethod.GET,
            GET_CHALLENGES_URL + "?page=" + page + "&size=" + size,
            HttpStatus.BAD_REQUEST
        );

        // THEN
    }
}