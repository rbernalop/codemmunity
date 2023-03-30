package org.rbernalop.apiexecution.script.infrastructure.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunQuery;
import org.rbernalop.apiexecution.shared.application.script.run.ScriptRunResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ScriptPostRunController extends ApiController {
    public ScriptPostRunController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("script/run")
    public ScriptPostRunResponse run(@RequestBody ScriptPostRunRequest request) {
        ScriptRunQuery scriptRunQuery = new ScriptRunQuery(request.getScript(), request.getLanguageId());
        ScriptRunResponse runResponse = ask(scriptRunQuery);
        return new ScriptPostRunResponse(runResponse.getCompilationOutput(), runResponse.getExecutionOutput());
    }
}

@NoArgsConstructor
@Getter
class ScriptPostRunRequest {
    private String script;
    private String languageId;
}

@NoArgsConstructor
@Getter
class ScriptPostRunResponse {
    private String compilationOutput;
    private String executionOutput;

    public ScriptPostRunResponse(String compilationOutput, String executionOutput) {
        this.compilationOutput = compilationOutput;
        this.executionOutput = executionOutput;
    }
}
