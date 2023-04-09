package org.rbernalop.servicesynchronization.script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.servicesynchronization.shared.application.script.create.CreateScriptCommandMother;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateScriptCommandHandlerTest extends UnitTestCase {
    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private ScriptManager scriptManager;

    @Mock
    private QueryBus queryBus;

    @InjectMocks
    private CreateScriptCommandHandler createScriptCommandHandler;

    @Test
    void should_create_script() {
        // GIVEN
        CreateScriptCommand command = CreateScriptCommandMother.random();

        // WHEN
        assertDoesNotThrow(() -> createScriptCommandHandler.handle(command));

        // THEN
        ScriptId id = new ScriptId(command.getId());
        ScriptContent content = new ScriptContent("");
        Script script = new Script(id, content);
        verify(scriptManager, times(1)).setScriptContent(id, content);
        verify(scriptRepository, times(1)).save(script);
    }
}