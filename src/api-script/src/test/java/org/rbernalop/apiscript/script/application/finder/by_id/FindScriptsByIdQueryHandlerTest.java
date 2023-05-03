package org.rbernalop.apiscript.script.application.finder.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.aggregate.ScriptMother;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdQueryMother;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdResponse;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindScriptsByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private ScriptRepository scriptRepository;



    @InjectMocks
    private FindScriptsByIdQueryHandler findScriptsByIdQueryHandler;

    @Test
    void should_find_script_by_id() {
        // GIVEN
        FindScriptsByIdQuery query = FindScriptsByIdQueryMother.random();
        Script expectedScript = ScriptMother.randomWithId(query.getId());
        FindScriptsByIdResponse expectedResponse = new FindScriptsByIdResponse(expectedScript);
        when(scriptRepository.findById(new ScriptId(query.getId()))).thenReturn(Optional.of(expectedScript));

        // WHEN
        FindScriptsByIdResponse actualResponse = assertDoesNotThrow(() -> findScriptsByIdQueryHandler.handle(query));

        // THEN
        assertEquals(expectedResponse, actualResponse);
        verify(scriptRepository, times(1)).findById(new ScriptId(query.getId()));
    }
}