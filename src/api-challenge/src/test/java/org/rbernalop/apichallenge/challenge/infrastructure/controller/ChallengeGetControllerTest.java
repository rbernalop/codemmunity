package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.*;

class ChallengeGetControllerTest extends IntegrationTestCase {
    private static final String GET_CHALLENGES_URL = "/api/v1/challenge";

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category savedCategory;

    @BeforeEach
    void setUp() {
        savedCategory = categoryRepository.save(ChallengeCategoryMother.random());
    }

    @AfterEach
    void tearDown() {
        challengeRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void should_get_all_challenges() throws Exception {
        // GIVEN
        Challenge challenge = ChallengeMother.randomFromCategory(savedCategory);
        challengeRepository.save(challenge);
        int page = 0;
        int size = 10;

        // WHEN
        MvcResult mvcResult = assertRequest(
            HttpMethod.GET,
        GET_CHALLENGES_URL + "?page=" + page + "&size=" + size,
            HttpStatus.OK
        );

        // THEN
        ChallengeGetResponses challengeGetResponses = objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), ChallengeGetResponses.class);
        assertEquals(1, challengeGetResponses.getChallenges().size());
        assertNotNull(challenge.getId());
        assertEquals(challenge.getId().getValue(), challengeGetResponses.getChallenges().get(0).getId());
        assertEquals(challenge.getTitle(), challengeGetResponses.getChallenges().get(0).getTitle());
        assertEquals(challenge.getCategory(), challengeGetResponses.getChallenges().get(0).getCategory());
        assertEquals(challenge.getDifficulty(), challengeGetResponses.getChallenges().get(0).getDifficulty());
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
    }
}