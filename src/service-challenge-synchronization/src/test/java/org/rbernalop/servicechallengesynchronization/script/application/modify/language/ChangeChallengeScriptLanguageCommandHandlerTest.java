package org.rbernalop.servicechallengesynchronization.script.application.modify.language;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScriptMother;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.language.ChangeChallengeScriptLanguageCommand;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.language.ChangeChallengeScriptLanguageCommandMother;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class ChangeChallengeScriptLanguageCommandHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeScriptManager challengeScriptManager;

    @Mock
    private ChallengeScriptRepository challengeScriptRepository;

    @InjectMocks
    private ChangeChallengeScriptLanguageCommandHandler handler;

    @Test
    void should_change_challenge_script_content() {
        // GIVEN
        ChangeChallengeScriptLanguageCommand command = ChangeChallengeScriptLanguageCommandMother.random();

        ChallengeId challengeId = new ChallengeId(command.getChallengeId());
        UserUsername username = new UserUsername(command.getUsername());
        LanguageName language = new LanguageName(command.getLanguageName());
        ChallengeScriptId challengeScriptId = new ChallengeScriptId(challengeId, username);
        ChallengeScript challengeScript = ChallengeScriptMother.fromIdAndLanguage(challengeScriptId, language);

        when(challengeScriptManager.getScript(challengeScriptId)).thenReturn(challengeScript);

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));
        
        // THEN
        verify(challengeScriptManager, times(1)).getScript(challengeScriptId);
        verify(challengeScriptRepository, never()).findById(any());
        verify(challengeScriptManager, never()).create(any());
        verify(challengeScriptManager, times(1)).setScriptLanguage(challengeScriptId, language);
    }
}