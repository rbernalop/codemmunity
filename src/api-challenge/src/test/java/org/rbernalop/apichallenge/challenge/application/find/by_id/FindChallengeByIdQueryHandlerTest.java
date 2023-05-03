package org.rbernalop.apichallenge.challenge.application.find.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.aggregate.ChallengeMother;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.completion.domain.value_object.LanguageNameMother;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdQuery;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdResponse;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdResponseMother;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdResponse;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.LanguageName;
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
        FindBaseCodeByIdResponse findBaseCodeByIdResponse =
            FindBaseCodeByIdResponseMother.fromChallengeId(challenge.getId().getValue());
        FindBaseCodeByIdQuery findBaseCodeByIdQuery =
            new FindBaseCodeByIdQuery(challenge.getId().getValue(), findBaseCodeByIdResponse.getLanguageName());

        assertNotNull(challenge.getId());
        when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.of(challenge));
        when(queryBus.ask(findBaseCodeByIdQuery)).thenReturn(findBaseCodeByIdResponse);

        // WHEN
        FindChallengeByIdResponse response = assertDoesNotThrow(() -> findChallengeByIdQueryHandler.handle(
            new FindChallengeByIdQuery(challenge.getId().getValue(), findBaseCodeByIdResponse.getLanguageName()))
        );

        // THEN
        verify(challengeRepository, times(1)).findById(challenge.getId());
        verify(queryBus, times(1)).ask(findBaseCodeByIdQuery);
        assertEquals(challenge.getId().getValue(), response.getId());
        assertEquals(challenge.getTitle(), response.getTitle());
        assertEquals(challenge.getDescription(), response.getDescription());
        assertEquals(challenge.getCategory(), response.getCategory());
        assertEquals(challenge.getDifficulty(), response.getDifficulty());
        assertEquals(challenge.getUserUsername(), response.getUserUsername());
        assertEquals(findBaseCodeByIdResponse.getCode(), response.getBaseCode());
    }

    @Test
    void should_throw_EntityNotFoundException_when_challenge_does_not_exist() {
        // GIVEN
        Challenge challenge = ChallengeMother.random();
        LanguageName languageName = LanguageNameMother.random();

        assertNotNull(challenge.getId());
        when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.empty());

        // WHEN
        assertThrows(EntityNotFoundException.class, () ->
            findChallengeByIdQueryHandler.handle(new FindChallengeByIdQuery(challenge.getId().getValue(), languageName.getValue())));

        // THEN
        verify(challengeRepository, times(1)).findById(challenge.getId());
        verify(queryBus, never()).ask(any(FindBaseCodeByIdQuery.class));
    }
}