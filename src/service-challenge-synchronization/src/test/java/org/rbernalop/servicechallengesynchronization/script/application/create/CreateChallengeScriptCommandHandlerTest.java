package org.rbernalop.servicechallengesynchronization.script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScriptMother;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.shared.application.script.create.CreateChallengeScriptCommand;
import org.rbernalop.servicechallengesynchronization.shared.application.script.create.CreateChallengeScriptCommandMother;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class CreateChallengeScriptCommandHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeScriptRepository challengeScriptRepository;

    @Mock
    private ChallengeScriptManager challengeScriptManager;

    @InjectMocks
    private CreateChallengeScriptCommandHandler createChallengeScriptCommandHandler;

    @Test
    void should_create_challenge_script() {
        // GIVEN
        ChallengeScript challengeScript = ChallengeScriptMother.random();
        CreateChallengeScriptCommand challengeScriptCommand = CreateChallengeScriptCommandMother.fromChallengeScript(challengeScript);

        when(challengeScriptRepository.existsById(challengeScript.getChallengeScriptId())).thenReturn(false);
        // WHEN
        assertDoesNotThrow(() -> createChallengeScriptCommandHandler.handle(challengeScriptCommand));

        // THEN
        verify(challengeScriptRepository, times(1)).existsById(challengeScript.getChallengeScriptId());
        verify(challengeScriptRepository, times(1)).save(challengeScript);
    }

    @Test
    void should_do_nothing_when_challenge_script_exists_in_db() {
        // GIVEN
        ChallengeScript challengeScript = ChallengeScriptMother.random();
        CreateChallengeScriptCommand challengeScriptCommand = CreateChallengeScriptCommandMother.fromChallengeScript(challengeScript);

        when(challengeScriptRepository.existsById(challengeScript.getChallengeScriptId())).thenReturn(true);

        // WHEN
        assertDoesNotThrow(() -> createChallengeScriptCommandHandler.handle(challengeScriptCommand));

        // THEN
        verify(challengeScriptRepository, times(1)).existsById(challengeScript.getChallengeScriptId());
        verify(challengeScriptManager, never()).create(challengeScript);
        verify(challengeScriptRepository, never()).save(challengeScript);
    }
}