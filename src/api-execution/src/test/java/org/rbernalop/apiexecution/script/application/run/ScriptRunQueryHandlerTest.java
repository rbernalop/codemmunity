package org.rbernalop.apiexecution.script.application.run;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.language.domain.aggregate.LanguageMother;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdQuery;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdResponse;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunQuery;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunQueryMother;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunResponse;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScriptRunQueryHandlerTest extends UnitTestCase {
    @InjectMocks
    private ScriptRunQueryHandler scriptRunQueryHandler;

    @Test
    void should_run_script() {
        // GIVEN
        Language language = LanguageMother.mockLanguage();
        assertNotNull(language.getId());
        String languageId = language.getId().getValue();
        ScriptRunQuery scriptRunQuery = ScriptRunQueryMother.randomByLanguageId(languageId);

        FindLanguageByIdQuery findLanguageByIdQuery = new FindLanguageByIdQuery(languageId);
        FindLanguageByIdResponse findLanguageByIdResponse = new FindLanguageByIdResponse(language);
        when(queryBus.ask(findLanguageByIdQuery)).thenReturn(findLanguageByIdResponse);

        ScriptRunResponse scriptRunResponse = new ScriptRunResponse();
        scriptRunResponse.setCompilationOutput("Compilation result");
        scriptRunResponse.setExecutionOutput("Execution result");

        // WHEN
        ScriptRunResponse runResponse = assertDoesNotThrow(() -> scriptRunQueryHandler.handle(scriptRunQuery));

        // THEN
        assertEquals(scriptRunResponse.getCompilationOutput(), runResponse.getCompilationOutput());
        assertEquals(scriptRunResponse.getExecutionOutput(), runResponse.getExecutionOutput());
        verify(queryBus, times(1)).ask(findLanguageByIdQuery);
    }
}