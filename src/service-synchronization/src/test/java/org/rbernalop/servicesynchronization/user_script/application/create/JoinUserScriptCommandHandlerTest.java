package org.rbernalop.servicesynchronization.user_script.application.create;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.shared.application.user_script.create.JoinUserScriptCommand;
import org.rbernalop.servicesynchronization.shared.application.user_script.create.JoinUserScriptCommandMother;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JoinUserScriptCommandHandlerTest extends UnitTestCase {
    @Mock
    private ScriptManager scriptManager;


    @InjectMocks
    private JoinUserScriptCommandHandler handler;

    @Test
    void should_join_user_to_script() {
        // GIVEN
        JoinUserScriptCommand command = JoinUserScriptCommandMother.random();
        ScriptId scriptId = new ScriptId(command.getScriptId());
        UserUsername username = new UserUsername(command.getUsername());

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

       // THEN
        verify(scriptManager, times(1)).addOnlineUser(scriptId, username);
    }
}