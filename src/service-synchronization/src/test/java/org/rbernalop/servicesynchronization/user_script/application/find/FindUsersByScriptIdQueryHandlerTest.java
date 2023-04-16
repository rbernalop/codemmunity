package org.rbernalop.servicesynchronization.user_script.application.find;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdQuery;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdQueryMother;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdResponse;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdResponseMother;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUsersByScriptIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private ScriptManager scriptManager;



    @InjectMocks
    private FindUsersByScriptIdQueryHandler findUsersByScriptIdQueryHandler;

    @Test
    void should_find_users_by_script_id() {
        // GIVEN
        FindUsersByScriptIdQuery query = FindUsersByScriptIdQueryMother.random();
        FindUsersByScriptIdResponse response = FindUsersByScriptIdResponseMother.random();
        ScriptId scriptId = new ScriptId(query.getScriptId());

        when(scriptManager.getOnlineUsernames(scriptId))
            .thenReturn(response.getUsernames().stream().map(UserUsername::new).toList());

        // WHEN
        FindUsersByScriptIdResponse actualResponse =
            assertDoesNotThrow(() -> findUsersByScriptIdQueryHandler.handle(query));

        // THEN
        verify(scriptManager, times(1)).getOnlineUsernames(scriptId);
        assertEquals(response, actualResponse);
    }

}