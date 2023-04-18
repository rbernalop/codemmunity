package org.rbernalop.servicesynchronization.script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.shared.application.script.create.ScriptCreatedDomainEventMother;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.ShareKey;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateScriptEventListenerTest extends UnitTestCase {
    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private ScriptManager scriptManager;



    @InjectMocks
    private CreateScriptEventListener createScriptCommandHandler;

    @Test
    void should_create_script() {
        // GIVEN
        org.rbernalop.shared.domain.bus.event.script.ScriptCreatedDomainEvent command = ScriptCreatedDomainEventMother.random();

        // WHEN
        assertDoesNotThrow(() -> createScriptCommandHandler.handle(command));

        // THEN
        ScriptId id = new ScriptId(command.getAggregateId());
        ShareKey key = new ShareKey(command.getShareKey());
        ScriptContent content = new ScriptContent("");
        Script script = Script.create(id, key, content);
        verify(scriptManager, times(1)).setScriptContent(id, content);
        verify(scriptRepository, times(1)).save(script);
    }
}