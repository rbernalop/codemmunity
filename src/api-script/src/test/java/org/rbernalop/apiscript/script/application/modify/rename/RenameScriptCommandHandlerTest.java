package org.rbernalop.apiscript.script.application.modify.rename;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.exception.InvalidScriptDataException;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.apiscript.shared.application.script.modify.RenameScriptCommand;
import org.rbernalop.apiscript.shared.application.script.modify.RenameScriptCommandMother;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.exception.NotAllowedOperationException;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RenameScriptCommandHandlerTest extends UnitTestCase {
    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private QueryBus queryBus;

    @InjectMocks
    private RenameScriptCommandHandler renameScriptCommandHandler;

    @Test
    public void should_rename_script() {
        // GIVEN
        Script scriptToRename = ScriptMother.random();
        assertNotNull(scriptToRename.getId());

        RenameScriptCommand command = RenameScriptCommandMother.randomFromScriptIdAndUsername(
            scriptToRename.getId().getValue(),
            scriptToRename.getOwnerName()
        );

        Script scriptRenamed = ScriptMother.fromScriptAndNewName(scriptToRename, command.getNextName());

        when(scriptRepository.findById(scriptToRename.getId())).thenReturn(Optional.of(scriptToRename));
        when(scriptRepository.save(scriptRenamed)).thenReturn(scriptRenamed);

        // WHEN
        assertDoesNotThrow(() -> renameScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, times(1)).findById(new ScriptId(command.getId()));
        verify(scriptRepository, times(1)).save(scriptRenamed);
    }

    @Test
    void should_throw_InvalidScriptDataException_when_script_name_is_empty() {
        // GIVEN
        RenameScriptCommand command = RenameScriptCommandMother.randomWithEmptyName();

        // WHEN
        assertThrows(InvalidScriptDataException.class, () -> renameScriptCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, never()).findById(new ScriptId(command.getId()));
        verify(scriptRepository, never()).save(any());
    }

    @Test
    void should_throw_EntityNotFoundException_when_script_does_not_exist() {
        // GIVEN
        RenameScriptCommand command = RenameScriptCommandMother.random();

        when(scriptRepository.findById(new ScriptId(command.getId()))).thenReturn(Optional.empty());

        // WHEN
        EntityNotFoundException exception =
            assertThrows(EntityNotFoundException.class, () -> renameScriptCommandHandler.handle(command));

        // THEN
        assertEquals("Script with id '" + command.getId() + "' not found", exception.getMessage());
        verify(scriptRepository, times(1)).findById(new ScriptId(command.getId()));
        verify(scriptRepository, never()).save(any());
    }

    @Test
    void should_throw_NotAllowedOperationException_when_command_username_is_different_from_script_username() {
        // GIVEN
        Script scriptToRename = ScriptMother.random();
        assertNotNull(scriptToRename.getId());
        RenameScriptCommand command = RenameScriptCommandMother.randomFromScriptIdAndUsername(
            scriptToRename.getId().getValue(),
            "differentUsername"
        );

        when(scriptRepository.findById(scriptToRename.getId())).thenReturn(Optional.of(scriptToRename));

        // WHEN
        NotAllowedOperationException exception =
            assertThrows(NotAllowedOperationException.class, () -> renameScriptCommandHandler.handle(command));

        // THEN
        assertEquals("You can't rename another user's script", exception.getMessage());
        verify(scriptRepository, times(1)).findById(new ScriptId(command.getId()));
        verify(scriptRepository, never()).save(any());
    }
}