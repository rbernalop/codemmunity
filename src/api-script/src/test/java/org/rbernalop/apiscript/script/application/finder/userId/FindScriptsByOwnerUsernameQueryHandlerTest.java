package org.rbernalop.apiscript.script.application.finder.userId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.OwnerUsername;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdQueryMother;
import org.rbernalop.apiscript.shared.domain.exception.NegativeException;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindScriptsByOwnerUsernameQueryHandlerTest extends UnitTestCase {

    @Mock
    private ScriptRepository scriptRepository;
    @Mock
    private QueryBus queryBus;

    private FindScriptsByUserIdQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        handler = new FindScriptsByUserIdQueryHandler(queryBus, scriptRepository);
    }

    @Test
    void should_find_scripts_by_user_id() {
        Script script = ScriptMother.random();
        String userId = script.getOwnerName();

        FindScriptsByUserIdQuery query = FindScriptsByUserIdQueryMother.randomFromUserId(userId);

        when(scriptRepository.findByOwnerUsername(new OwnerUsername(userId), PageRequest.of(query.getPage(), query.getSize()))).thenReturn(List.of(script));

        var response = handler.handle(query);

        assertNotNull(script.getId());
        assertEquals(script.getId().getValue(), response.getScriptsResponses().get(0).getId());
        assertEquals(script.getName(), response.getScriptsResponses().get(0).getName());
        assertEquals(script.getOwnerName(), response.getScriptsResponses().get(0).getOwnerId());
        assertEquals(script.getLanguageId(), response.getScriptsResponses().get(0).getLanguageId());
        assertEquals(script.getShareKey(), response.getScriptsResponses().get(0).getShareKey());

        verify(scriptRepository, times(1)).findByOwnerUsername(new OwnerUsername(userId), PageRequest.of(query.getPage(), query.getSize()));
    }

    @Test
    void should_throw_NegativeException_when_page_is_negative() {
        FindScriptsByUserIdQuery query = FindScriptsByUserIdQueryMother.withNegativePage();

        NegativeException exception = assertThrows(NegativeException.class, () -> handler.handle(query));

        assertEquals("Page must be greater than 0", exception.getMessage());
        verify(scriptRepository, never()).findByOwnerUsername(any(), any());
    }

    @Test
    void should_throw_NegativeException_when_size_is_negative() {
        FindScriptsByUserIdQuery query = FindScriptsByUserIdQueryMother.withNegativeSize();

        NegativeException exception = assertThrows(NegativeException.class, () -> handler.handle(query));

        assertEquals("Size must be greater than 0", exception.getMessage());
        verify(scriptRepository, never()).findByOwnerUsername(any(), any());
    }
}