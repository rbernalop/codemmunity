package org.rbernalop.apiscript.script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.exception.InvalidScriptDataException;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommandMother;
import org.rbernalop.shared.domain.InvalidIdException;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateScriptCommandHandlerTest extends UnitTestCase {

    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private QueryBus queryBus;

    @InjectMocks
    private CreateScriptCommandHandler createScriptCommandHandler;

    @Test
    void should_create_a_script() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        when(scriptRepository.save(any())).thenReturn(null);

        assertDoesNotThrow(() -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, times(1)).save(any());
    }

    @Test
    void should_throw_InvalidIdException_when_id_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setId(null);

        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
    }

    @Test
    void should_throw_InvalidIdException_when_ShareKey_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setKey(null);

        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
    }

    @Test
    void should_throw_InvalidIdException_when_ScriptLanguageId_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setLanguageId(null);

        assertThrows(InvalidIdException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
    }

    @Test
    void should_throw_InvalidScriptNameException_when_ScriptName_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setOwnerUserName(null);

        assertThrows(InvalidScriptDataException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
    }

    @Test
    void should_throw_InvalidScriptNameException_when_OwnerUsername_is_invalid() {
        CreateScriptCommand command = CreateScriptCommandMother.random();
        command.setOwnerUserName(null);

        assertThrows(InvalidScriptDataException.class, () -> createScriptCommandHandler.handle(command));

        verify(scriptRepository, never()).save(any());
    }
}