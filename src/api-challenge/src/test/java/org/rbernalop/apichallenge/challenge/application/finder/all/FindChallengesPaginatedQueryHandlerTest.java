package org.rbernalop.apichallenge.challenge.application.finder.all;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedQuery;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedQueryMother;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedResponse;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindChallengesPaginatedQueryHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeRepository challengeRepository;



    @InjectMocks
    private FindChallengesPaginatedQueryHandler findChallengesPaginatedQueryHandler;


    @Test
    void should_find_challenges_paginated() {
        // GIVEN
        FindChallengesPaginatedQuery query = FindChallengesPaginatedQueryMother.random();
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getSize());

        List<Challenge> challenges = List.of(ChallengeMother.random());

        when(challengeRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(challenges));

        // WHEN
        FindChallengesPaginatedResponse response =
            assertDoesNotThrow(() -> findChallengesPaginatedQueryHandler.handle(query));

        // THEN
        assertEquals(challenges.size(), response.getChallenges().size());
        assertNotNull(challenges.get(0).getId());
        assertEquals(challenges.get(0).getId().getValue(), response.getChallenges().get(0).getId());
        assertEquals(challenges.get(0).getTitle(), response.getChallenges().get(0).getTitle());
        assertEquals(challenges.get(0).getDescription(), response.getChallenges().get(0).getDescription());
        assertEquals(challenges.get(0).getCategory(), response.getChallenges().get(0).getCategory());
        assertEquals(challenges.get(0).getDifficulty(), response.getChallenges().get(0).getDifficulty());
        assertEquals(challenges.get(0).getUserUsername(), response.getChallenges().get(0).getUserUsername());
    }
}