package org.rbernalop.apichallenge.challenge.application.find.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdResponse;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindChallengeByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private FindChallengeByIdQueryHandler findChallengeByIdQueryHandler;

    @Test
    void should_find_challenge_by_id() {
        // GIVEN
        Challenge challenge = ChallengeMother.random();

        assertNotNull(challenge.getId());
        when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.of(challenge));

        // WHEN
        FindChallengeByIdResponse response = assertDoesNotThrow(() ->
            findChallengeByIdQueryHandler.handle(new FindChallengeByIdQuery(challenge.getId().getValue())));

        // THEN
        verify(challengeRepository, times(1)).findById(challenge.getId());
        assertEquals(challenge.getId().getValue(), response.getId());
        assertEquals(challenge.getTitle(), response.getTitle());
        assertEquals(challenge.getDescription(), response.getDescription());
        assertEquals(challenge.getCategory(), response.getCategory());
        assertEquals(challenge.getDifficulty(), response.getDifficulty());
        assertEquals(challenge.getUserUsername(), response.getUserUsername());
    }

    @Test
    void should_throw_EntityNotFoundException_when_challenge_does_not_exist() {
        // GIVEN
        Challenge challenge = ChallengeMother.random();

        assertNotNull(challenge.getId());
        when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.empty());

        // WHEN
        assertThrows(EntityNotFoundException.class, () ->
            findChallengeByIdQueryHandler.handle(new FindChallengeByIdQuery(challenge.getId().getValue())));

        // THEN
        verify(challengeRepository, times(1)).findById(challenge.getId());
    }
}