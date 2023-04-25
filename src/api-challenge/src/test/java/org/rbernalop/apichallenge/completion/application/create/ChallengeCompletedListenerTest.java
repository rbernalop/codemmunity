package org.rbernalop.apichallenge.completion.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.shared.domain.bus.event.test.ChallengeCompletedDomainEvent;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ChallengeCompletedListenerTest extends UnitTestCase {
    @Mock
    private CompletionRepository completionRepository;

    @InjectMocks
    private ChallengeCompletedListener challengeCompletedListener;

    @Test
    void should_create_completion_when_challenge_completed_event_is_received() {
        // GIVEN
        ChallengeCompletedDomainEvent event = ChallengeCompletedDomainEventMother.random();

        // WHEN
        assertDoesNotThrow(() -> challengeCompletedListener.handle(event));

        // THEN
        verify(completionRepository, times(1)).save(any());
    }
}