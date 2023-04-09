package org.rbernalop.servicesynchronization.user_script.application.delete;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.shared.application.user_script.delete.LeaveUserScriptCommand;
import org.rbernalop.servicesynchronization.shared.application.user_script.delete.LeaveUserScriptCommandMother;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LeaveUserScriptCommandHandlerTest extends UnitTestCase {
    @Mock
    private ScriptManager scriptManager;

    @Mock
    private QueryBus queryBus;

    @InjectMocks
    private LeaveUserScriptCommandHandler handler;

    @Test
    void should_leave_user_from_script() {
        // GIVEN
        LeaveUserScriptCommand command = LeaveUserScriptCommandMother.random();
        ScriptId scriptId = new ScriptId(command.getScriptId());
        UserUsername username = new UserUsername(command.getUsername());

        // WHEN
        assertDoesNotThrow(() -> handler.handle(command));

        // THEN
        verify(scriptManager, times(1)).removeOnlineUser(scriptId, username);
    }
}