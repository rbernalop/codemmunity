package org.rbernalop.servicesynchronization.script.application.find;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdQuery;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdQueryMother;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdResponse;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdResponseMother;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdQuery;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdResponse;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindScriptByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private QueryBus queryBus;
    @Mock
    private ScriptRepository scriptRepository;
    @Mock
    private ScriptManager scriptManager;
    @InjectMocks
    private FindScriptByIdQueryHandler findScriptByIdQueryHandler;

    @Test
    void should_find_locally_script_by_id() {
        // GIVEN
        FindScriptByIdQuery query = FindScriptByIdQueryMother.random();
        FindScriptByIdResponse expectedResponse = FindScriptByIdResponseMother.random();
        ScriptId scriptId = new ScriptId(query.getId());
        ScriptContent scriptContent = new ScriptContent(expectedResponse.getContent());

        when(scriptManager.getScriptContent(scriptId)).thenReturn(scriptContent);
        when(queryBus.ask(any(FindUsersByScriptIdQuery.class)))
            .thenReturn(new FindUsersByScriptIdResponse(expectedResponse.getOnlineUsers()));

        // WHEN
        FindScriptByIdResponse response = assertDoesNotThrow(() -> findScriptByIdQueryHandler.handle(query));

        // THEN
        verify(scriptManager,times(1)).getScriptContent(scriptId);
        verify(queryBus,times(1)).ask(any(FindUsersByScriptIdQuery.class));
        assertEquals(expectedResponse, response);
    }

    @Test
    void should_find_in_database_script_by_id() {
        // GIVEN
        FindScriptByIdQuery query = FindScriptByIdQueryMother.random();
        FindScriptByIdResponse expectedResponse = FindScriptByIdResponseMother.random();
        ScriptId scriptId = new ScriptId(query.getId());
        ScriptContent scriptContent = new ScriptContent(expectedResponse.getContent());
        Script script = Script.create(scriptId, scriptContent);

        when(scriptManager.getScriptContent(scriptId)).thenReturn(null);
        when(scriptRepository.findById(scriptId)).thenReturn(Optional.of(script));
        when(queryBus.ask(any(FindUsersByScriptIdQuery.class)))
            .thenReturn(new FindUsersByScriptIdResponse(expectedResponse.getOnlineUsers()));

        // WHEN
        FindScriptByIdResponse response = assertDoesNotThrow(() -> findScriptByIdQueryHandler.handle(query));

        // THEN
        verify(scriptManager,times(1)).getScriptContent(scriptId);
        verify(scriptRepository,times(1)).findById(scriptId);
        verify(scriptManager,times(1)).setScriptContent(scriptId, scriptContent);
        verify(queryBus,times(1)).ask(any(FindUsersByScriptIdQuery.class));
        assertEquals(expectedResponse, response);
    }

    @Test
    void should_throw_EntityNotFoundException_when_script_does_not_exists() {
        // GIVEN
        FindScriptByIdQuery query = FindScriptByIdQueryMother.random();
        ScriptId scriptId = new ScriptId(query.getId());

        when(scriptManager.getScriptContent(scriptId)).thenReturn(null);
        when(scriptRepository.findById(scriptId)).thenReturn(Optional.empty());

        // WHEN
        EntityNotFoundException exception =
            assertThrows(EntityNotFoundException.class, () -> findScriptByIdQueryHandler.handle(query));

        // THEN
        verify(scriptManager,times(1)).getScriptContent(scriptId);
        verify(scriptRepository,times(1)).findById(scriptId);
        verify(scriptManager, never()).setScriptContent(any(), any());
        verify(queryBus, never()).ask(any());
        assertEquals("Script with id " + query.getId() + " not found", exception.getMessage());
    }
}