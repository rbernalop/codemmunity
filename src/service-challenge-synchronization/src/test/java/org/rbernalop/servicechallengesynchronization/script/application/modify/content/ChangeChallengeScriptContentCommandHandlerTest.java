package org.rbernalop.servicechallengesynchronization.script.application.modify.content;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content.ChangeChallengeScriptContentCommand;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content.ChangeChallengeScriptContentCommandMother;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ChangeChallengeScriptContentCommandHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeScriptManager challengeScriptManager;

    @InjectMocks
    private ChangeChallengeScriptContentCommandHandler handler;

    @Test
    void should_change_challenge_script_content() {
        // GIVEN
        ChangeChallengeScriptContentCommand command = ChangeChallengeScriptContentCommandMother.random();

        ChallengeId challengeId = new ChallengeId(command.getChallengeId());
        UserUsername username = new UserUsername(command.getUsername());
        ScriptContent content = new ScriptContent(command.getContent());
        ChallengeScriptId challengeScriptId = new ChallengeScriptId(challengeId, username);

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

        // THEN
        verify(challengeScriptManager, times(1)).setScriptContent(challengeScriptId, content);
    }
}