package org.rbernalop.apiscript.script.application.finder.userId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.valueobject.UserId;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdQuery;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindScriptsByUserIdQueryHandlerTest extends UnitTestCase {

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
        String userId = script.getOwnerId();
        int page = 0;
        int size = 50;

        FindScriptsByUserIdQuery query = new FindScriptsByUserIdQuery(userId, page, size);

        when(scriptRepository.findByOwnerId(new UserId(userId), PageRequest.of(page, size))).thenReturn(List.of(script));

        var response = handler.handle(query);

        assertEquals(script.getId(), response.getScriptsResponses().get(0).getId());
        assertEquals(script.getName(), response.getScriptsResponses().get(0).getName());
        assertEquals(script.getOwnerId(), response.getScriptsResponses().get(0).getOwnerId());
        assertEquals(script.getLanguageId(), response.getScriptsResponses().get(0).getLanguageId());
        assertEquals(script.getShareKey(), response.getScriptsResponses().get(0).getShareKey());
    }
}