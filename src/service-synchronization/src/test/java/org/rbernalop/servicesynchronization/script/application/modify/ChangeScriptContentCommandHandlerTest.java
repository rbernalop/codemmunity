package org.rbernalop.servicesynchronization.script.application.modify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.application.script.modify.ChangeScriptContentCommand;
import org.rbernalop.servicesynchronization.shared.application.script.modify.ChangeScriptContentCommandMother;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ChangeScriptContentCommandHandlerTest extends UnitTestCase {
    @Mock
    private ScriptManager scriptManager;

    @Mock
    private QueryBus queryBus;

    @InjectMocks
    private ChangeScriptContentCommandHandler changeScriptContentCommandHandler;

    @Test
    void should_change_script_content() {
        // GIVEN
        ChangeScriptContentCommand command = ChangeScriptContentCommandMother.random();
        ScriptId id = new ScriptId(command.getId());
        ScriptContent content = new ScriptContent(command.getContent());

        // WHEN
        assertDoesNotThrow(() -> changeScriptContentCommandHandler.handle(command));

        // THEN
        verify(scriptManager, times(1)).setScriptContent(id, content);
    }
}