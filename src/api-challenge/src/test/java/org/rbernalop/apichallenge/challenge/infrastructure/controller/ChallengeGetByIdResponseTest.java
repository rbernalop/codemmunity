package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.apichallenge.challenge.domain.entity.ChallengeCategoryMother;
import org.rbernalop.apichallenge.challenge.domain.port.CategoryRepository;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeIdMother;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeGetByIdResponseTest extends IntegrationTestCase {
    private final static String CHALLENGE_GET_BY_ID_URL = "/api/v1/challenge/";

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
    void should_get_all_challenges() throws Exception {
        // GIVEN
        Category category = categoryRepository.save(ChallengeCategoryMother.random());
        Challenge challenge = ChallengeMother.randomFromCategory(category);
        challengeRepository.save(challenge);

        // WHEN
        assertNotNull(challenge.getId());
        MvcResult mvcResult = assertRequest(
            HttpMethod.GET,
            CHALLENGE_GET_BY_ID_URL + challenge.getId().getValue(),
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
    }

    @Test
    void should_return_not_found_when_challenge_does_not_exist() throws Exception {
        // GIVEN
        String challengeId = ChallengeIdMother.random().getValue();

        // WHEN
        assertRequest(
            HttpMethod.GET,
            CHALLENGE_GET_BY_ID_URL + challengeId,
            HttpStatus.NOT_FOUND
        );

        // THEN
    }
}