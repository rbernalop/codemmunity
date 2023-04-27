package org.rbernalop.apichallenge.challenge.application.find.all;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.completion.domain.aggregate.CompletionMother;
import org.rbernalop.apichallenge.shared.application.challenge.find.ChallengeResponse;
import org.rbernalop.apichallenge.shared.application.challenge.find.all.FindChallengesPaginatedQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.all.FindChallengesPaginatedQueryMother;
import org.rbernalop.apichallenge.shared.application.challenge.find.all.FindChallengesPaginatedResponse;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByIdsQuery;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByIdsResponse;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindChallengesPaginatedQueryHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private FindChallengesPaginatedQueryHandler findChallengesPaginatedQueryHandler;

    @Test
    void should_find_challenges_paginated() {
        // GIVEN
        FindChallengesPaginatedQuery query = FindChallengesPaginatedQueryMother.random();
        Challenge expectedChallenge = ChallengeMother.random();
        List<Challenge> challenges = List.of(expectedChallenge);

        assertNotNull(expectedChallenge.getId());
        String challengeId = expectedChallenge.getId().getValue();

        FindUserCompletionsByIdsQuery findUserCompletionsByIdsQuery =
            new FindUserCompletionsByIdsQuery(List.of(challengeId), query.getRequesterUsername());
        FindUserCompletionsByIdsResponse findUserCompletionsByIdsResponse =
            FindUserCompletionsByIdsResponse.from(
                List.of(CompletionMother.fromChallengeIdAndUsername(challengeId, query.getRequesterUsername()))
            );


        when(challengeRepository.findAll(PageRequest.of(query.getPage(), query.getSize()))).thenReturn(new PageImpl<>(challenges));
        when(challengeRepository.count()).thenReturn((long) challenges.size());
        when(queryBus.ask(findUserCompletionsByIdsQuery)).thenReturn(findUserCompletionsByIdsResponse);

        // WHEN
        FindChallengesPaginatedResponse response =
            assertDoesNotThrow(() -> findChallengesPaginatedQueryHandler.handle(query));

        ChallengeResponse actualChallenge = response.getChallenges().get(0);

        // THEN
        verify(challengeRepository, times(1)).findAll(PageRequest.of(query.getPage(), query.getSize()));
        verify(challengeRepository, times(1)).count();
        verify(queryBus, times(1)).ask(findUserCompletionsByIdsQuery);

        assertEquals(challenges.size(), response.getChallenges().size());

        assertEquals(expectedChallenge.getId().getValue(), actualChallenge.getId());
        assertEquals(expectedChallenge.getTitle(), actualChallenge.getTitle());
        assertEquals(expectedChallenge.getDescription(), actualChallenge.getDescription());
        assertEquals(expectedChallenge.getCategory(), actualChallenge.getCategory());
        assertEquals(expectedChallenge.getDifficulty(), actualChallenge.getDifficulty());
        assertEquals(expectedChallenge.getUserUsername(), actualChallenge.getUserUsername());
        assertTrue(actualChallenge.isCompleted());
    }
}