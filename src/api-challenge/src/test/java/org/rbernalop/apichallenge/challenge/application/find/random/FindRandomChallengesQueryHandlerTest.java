package org.rbernalop.apichallenge.challenge.application.find.random;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids.FindBaseCodeByChallengeIdsResponses;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids.FindBaseCodeByChallengeIdsResponsesMother;
import org.rbernalop.apichallenge.shared.application.challenge.find.ChallengeResponse;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesResponse;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindRandomChallengesQueryHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private FindRandomChallengesQueryHandler findRandomChallengesQueryHandler;

    @Test
    void should_find_random_challenges() {
        // GIVEN
        Challenge expectedChallenge = ChallengeMother.random();
        List<Challenge> challenges = List.of(expectedChallenge);
        FindRandomChallengesQuery query = new FindRandomChallengesQuery(challenges.size());
        FindBaseCodeByChallengeIdsResponses baseCodeResponses = FindBaseCodeByChallengeIdsResponsesMother.random();

        when(challengeRepository.count()).thenReturn((long) challenges.size());
        when(challengeRepository.findRandomChallenges(challenges.size())).thenReturn(challenges);
        when(queryBus.ask(any())).thenReturn(baseCodeResponses);

        // WHEN
        FindRandomChallengesResponse response = assertDoesNotThrow(() -> findRandomChallengesQueryHandler.handle(query));

        // THEN
        verify(challengeRepository, times(1)).count();
        verify(challengeRepository, times(1)).findRandomChallenges(challenges.size());
        verify(queryBus, times(1)).ask(any());
        assertEquals(1, response.getChallenges().size());
        ChallengeResponse actualChallenge = response.getChallenges().get(0);
        assertNotNull(expectedChallenge.getId());
        assertEquals(expectedChallenge.getId().getValue(), actualChallenge.getId());
        assertEquals(expectedChallenge.getCategory(), actualChallenge.getCategory());
        assertEquals(expectedChallenge.getTitle(), actualChallenge.getTitle());
        assertEquals(expectedChallenge.getUserUsername(), actualChallenge.getUserUsername());
        assertEquals(expectedChallenge.getDescription(), actualChallenge.getDescription());
        assertEquals(expectedChallenge.getDifficulty(), actualChallenge.getDifficulty());
    }

}