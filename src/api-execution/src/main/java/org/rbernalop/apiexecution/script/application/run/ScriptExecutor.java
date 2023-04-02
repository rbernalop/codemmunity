package org.rbernalop.apiexecution.script.application.run;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunnerFactory;
import org.rbernalop.apiexecution.script.domain.value_object.ScriptContent;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdQuery;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdResponse;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunResponse;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.LanguageId;

public class ScriptExecutor {
    private final QueryBus queryBus;

    public ScriptExecutor(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    public ScriptRunResponse run(ScriptContent script, LanguageId languageId) {

        FindLanguageByIdQuery query = new FindLanguageByIdQuery(languageId.getValue());
        FindLanguageByIdResponse languageData = queryBus.ask(query);

        ScriptRunner scriptRunner = ScriptRunnerFactory.get(languageData.getName());
        RunResult result = scriptRunner.run(script.getValue());

        ScriptRunResponse response = new ScriptRunResponse();
        response.setCompilationOutput(result.getCompilationResult());
        response.setExecutionOutput(result.getExecutionResult());
        return response;
    }
}
