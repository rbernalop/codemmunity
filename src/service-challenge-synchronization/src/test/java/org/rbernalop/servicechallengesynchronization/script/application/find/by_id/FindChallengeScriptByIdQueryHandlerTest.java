package org.rbernalop.servicechallengesynchronization.script.application.find.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScriptMother;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindChallengeScriptByIdQuery;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindScriptByChallengeIdResponse;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindChallengeScriptByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private ChallengeScriptRepository challengeScriptRepository;
    @Mock
    private ChallengeScriptManager challengeScriptManager;

    @InjectMocks
    private FindChallengeScriptByIdQueryHandler handler;

    @Test
    void should_find_local_challenge_script_by_id() {
        // GIVEN
        ChallengeScript challengeScript = ChallengeScriptMother.random();
        FindChallengeScriptByIdQuery query = FindChallengeScriptByIdQuery.fromChallengeScriptId(challengeScript.getChallengeScriptId());

        when(challengeScriptManager.getScript(challengeScript.getChallengeScriptId())).thenReturn(challengeScript);
        // WHEN
        FindScriptByChallengeIdResponse response = assertDoesNotThrow(() -> handler.handle(query));

        // THEN
        verify(challengeScriptManager, times(1)).getScript(challengeScript.getChallengeScriptId());
        verify(challengeScriptRepository, never()).findById(any());
        verify(challengeScriptManager, never()).create(challengeScript);
        assertEquals(challengeScript.getScriptContent(), response.getContent());
        assertEquals(challengeScript.getLanguageName(), response.getLanguageName());
    }

    @Test
    void should_find_database_challenge_script_by_id() {
        // GIVEN
        ChallengeScript challengeScript = ChallengeScriptMother.random();
        FindChallengeScriptByIdQuery query = FindChallengeScriptByIdQuery.fromChallengeScriptId(challengeScript.getChallengeScriptId());

        when(challengeScriptManager.getScript(challengeScript.getChallengeScriptId())).thenReturn(null);
        when(challengeScriptRepository.findById(challengeScript.getChallengeScriptId())).thenReturn(Optional.of(challengeScript));

        // WHEN
        FindScriptByChallengeIdResponse response = assertDoesNotThrow(() -> handler.handle(query));

        // THEN
        verify(challengeScriptManager, times(1)).getScript(challengeScript.getChallengeScriptId());
        verify(challengeScriptRepository, times(1)).findById(challengeScript.getChallengeScriptId());
        verify(challengeScriptManager, times(1)).create(challengeScript);
        assertEquals(challengeScript.getScriptContent(), response.getContent());
        assertEquals(challengeScript.getLanguageName(), response.getLanguageName());
    }

    @Test
    void should_return_null_when_challenge_script_not_found() {
        // GIVEN
        ChallengeScript challengeScript = ChallengeScriptMother.random();
        FindChallengeScriptByIdQuery query = FindChallengeScriptByIdQuery.fromChallengeScriptId(challengeScript.getChallengeScriptId());

        when(challengeScriptManager.getScript(challengeScript.getChallengeScriptId())).thenReturn(null);
        when(challengeScriptRepository.findById(challengeScript.getChallengeScriptId())).thenReturn(Optional.empty());

        // WHEN
        FindScriptByChallengeIdResponse response = assertDoesNotThrow(() -> handler.handle(query));

        // THEN
        verify(challengeScriptManager, times(1)).getScript(challengeScript.getChallengeScriptId());
        verify(challengeScriptRepository, times(1)).findById(challengeScript.getChallengeScriptId());
        verify(challengeScriptManager, never()).create(challengeScript);
        assertNull(response);
    }
}