package org.rbernalop.apiscript.script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommandMother;
import org.rbernalop.shared.domain.InvalidIdException;
import org.rbernalop.shared.domain.exception.InvalidUserDataException;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateScriptCommandHandlerTest extends UnitTestCase {

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private CreateScriptCommandHandler createScriptCommandHandler;

    @Test
    void should_create_a_script() {
        // GIVEN
        CreateScriptCommand command = CreateScriptCommandMother.random();
        when(scriptRepository.save(any())).thenReturn(null);

        // WHEN
        assertDoesNotThrow(() -> createScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, times(1)).save(any());
        verify(eventBus, times(1)).publish(any());
    }

    @Test
    void should_throw_InvalidIdException_when_id_is_invalid() {
        // GIVEN
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setId(null);

        // WHEN
        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void should_throw_InvalidIdException_when_ShareKey_is_invalid() {
        // GIVEN
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setKey(null);

        // WHEN
        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void should_throw_InvalidIdException_when_ScriptLanguageId_is_invalid() {
        // GIVEN
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setLanguageId(null);

        // WHEN
        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void should_throw_InvalidScriptNameException_when_userUsername_is_invalid() {
        // GIVEN
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setUserUsername(null);

        // WHEN
        assertThrows(InvalidUserDataException.class, () -> createScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }
}