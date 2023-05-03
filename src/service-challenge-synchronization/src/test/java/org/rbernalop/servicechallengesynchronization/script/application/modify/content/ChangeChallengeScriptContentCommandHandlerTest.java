package org.rbernalop.servicechallengesynchronization.script.application.modify.content;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScriptMother;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content.ChangeChallengeScriptContentCommand;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content.ChangeChallengeScriptContentCommandMother;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class ChangeChallengeScriptContentCommandHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeScriptManager challengeScriptManager;

    @Mock
    private ChallengeScriptRepository challengeScriptRepository;

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
        ChallengeScript challengeScript = ChallengeScriptMother.fromIdAndContent(challengeScriptId, content);

        when(challengeScriptManager.getScript(challengeScriptId)).thenReturn(challengeScript);

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

        // THEN
        verify(challengeScriptManager, times(1)).getScript(challengeScriptId);
        verify(challengeScriptRepository, never()).findById(any());
        verify(challengeScriptManager, never()).create(any());
        verify(challengeScriptManager, times(1)).setScriptContent(challengeScriptId, content);
    }

    @Test
    void should_change_challenge_obtained_from_database_script_content() {
        // GIVEN
        ChangeChallengeScriptContentCommand command = ChangeChallengeScriptContentCommandMother.random();

        ChallengeId challengeId = new ChallengeId(command.getChallengeId());
        UserUsername username = new UserUsername(command.getUsername());
        ScriptContent content = new ScriptContent(command.getContent());
        ChallengeScriptId challengeScriptId = new ChallengeScriptId(challengeId, username);
        ChallengeScript challengeScript = ChallengeScriptMother.fromIdAndContent(challengeScriptId, content);

        when(challengeScriptManager.getScript(challengeScriptId)).thenReturn(null);
        when(challengeScriptRepository.findById(challengeScriptId)).thenReturn(Optional.of(challengeScript));

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

        // THEN
        verify(challengeScriptManager, times(1)).getScript(challengeScriptId);
        verify(challengeScriptRepository, times(1)).findById(challengeScriptId);
        verify(challengeScriptManager, times(1)).create(challengeScript);
        verify(challengeScriptManager, times(1)).setScriptContent(challengeScriptId, content);
    }
}