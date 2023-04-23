package org.rbernalop.apitest.test.application.send_results;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apitest.shared.application.test.send_results.SendTestsCompletionCommand;
import org.rbernalop.apitest.shared.application.test.send_results.SendTestsCompletionCommandMother;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SendTestsCompletionCommandHandlerTest extends UnitTestCase {
    @Mock
    private EventBus eventBus;

    @InjectMocks
    private SendTestsCompletionCommandHandler sendTestsCompletionCommandHandler;

    @Test
    void should_send_results_to_challenge_api_when_tests_are_passed() {
        // GIVEN
        SendTestsCompletionCommand command = SendTestsCompletionCommandMother.randomFromPassed(true);

        // WHEN
        assertDoesNotThrow(() -> sendTestsCompletionCommandHandler.handle(command));

        // THEN
        verify(eventBus, times(1)).publish(any());
    }

    @Test
    void should_not_send_results_to_challenge_api_when_tests_are_not_passed() {
        // GIVEN
        SendTestsCompletionCommand command = SendTestsCompletionCommandMother.randomFromPassed(false);

        // WHEN
        assertDoesNotThrow(() -> sendTestsCompletionCommandHandler.handle(command));

        // THEN
        verify(eventBus, never()).publish(any());
    }
}