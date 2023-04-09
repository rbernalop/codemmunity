package org.rbernalop.apiscript.script.application.modify.language;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.apiscript.shared.application.script.modify.ChangeScriptLanguageCommand;
import org.rbernalop.apiscript.shared.application.script.modify.ChangeScriptLanguageCommandMother;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChangeScriptLanguageCommandHandlerTest extends UnitTestCase {
    @Mock
    private QueryBus queryBus;

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private ChangeScriptLanguageCommandHandler changeScriptLanguageCommandHandler;

    @Test
    void should_change_script_language() {
        // GIVEN
        Script script = ScriptMother.random();
        assertNotNull(script.getId());
        ChangeScriptLanguageCommand command =
            ChangeScriptLanguageCommandMother.randomFromScriptId(script.getId().getValue());
        when(scriptRepository.findById(new ScriptId(command.getId()))).thenReturn(Optional.of(script));
        when(scriptRepository.save(script)).thenReturn(script);

        // WHEN
        assertDoesNotThrow(() -> changeScriptLanguageCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, times(1)).findById(new ScriptId(command.getId()));
        verify(scriptRepository, times(1)).save(script);
    }
}