package org.rbernalop.apiexecution.script.application.run;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunQuery;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

@Service
@Slf4j
public class ScriptRunQueryHandler implements QueryHandler<ScriptRunQuery, ScriptRunResponse> {

    private final ScriptExecutor scriptExecutor;

    public ScriptRunQueryHandler(QueryBus queryBus) {
        this.scriptExecutor = new ScriptExecutor(queryBus);
    }

    @Override
    public ScriptRunResponse handle(ScriptRunQuery query) {
        log.info("Running script: {} with language: {}", query.getScript(), query.getLanguageId());
        ScriptContent scriptContent = new ScriptContent(query.getScript());
        LanguageId languageId = new LanguageId(query.getLanguageId());
        return scriptExecutor.run(scriptContent, languageId);
    }
}
