package org.rbernalop.apiscript.script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommandMother;
import org.rbernalop.shared.domain.InvalidIdException;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.InvalidUserDataException;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateScriptCommandHandlerTest extends UnitTestCase {

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private CreateScriptCommandHandler createScriptCommandHandler;

    @Test
    void should_create_a_script() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        when(scriptRepository.save(any())).thenReturn(null);

        assertDoesNotThrow(() -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, times(1)).save(any());
        verify(eventBus, times(1)).publish(any());
    }

    @Test
    void should_throw_InvalidIdException_when_id_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setId(null);

        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void should_throw_InvalidIdException_when_ShareKey_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setKey(null);

        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void should_throw_InvalidIdException_when_ScriptLanguageId_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setLanguageId(null);

        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void should_throw_InvalidScriptNameException_when_userUsername_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setUserUsername(null);

        assertThrows(InvalidUserDataException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }
}