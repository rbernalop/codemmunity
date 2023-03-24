package org.rbernalop.apiscript.script.application.modify.share_key;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.shared.application.script.modify.RegenerateShareKeyCommand;
import org.rbernalop.apiscript.shared.application.script.modify.RegenerateShareKeyCommandMother;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegenerateShareKeyCommandHandlerTest extends UnitTestCase {
    @Mock
    private QueryBus queryBus;

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private RegenerateShareKeyCommandHandler regenerateShareKeyCommandHandler;

    @Test
    void should_regenerate_share_key() {
        // GIVEN
        Script script = ScriptMother.random();
        assertNotNull(script.getId());
        RegenerateShareKeyCommand command = RegenerateShareKeyCommandMother.randomFromScriptId(script.getId().getValue());
        Script scriptWithNewShareKey = ScriptMother.fromScriptAndNewShareKey(script, command.getShareKey());
        when(scriptRepository.findById(script.getId())).thenReturn(Optional.of(script));
        when(scriptRepository.save(scriptWithNewShareKey)).thenReturn(scriptWithNewShareKey);

        // WHEN
        assertDoesNotThrow(() -> regenerateShareKeyCommandHandler.handle(command));

        // THEN
        verify(scriptRepository, times(1)).findById(script.getId());
        verify(scriptRepository, times(1)).save(scriptWithNewShareKey);
    }
}