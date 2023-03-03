package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdResponse;
import org.rbernalop.apiscript.shared.application.script.find.ScriptResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ScriptGetByUserIdController extends ApiController {

    public ScriptGetByUserIdController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("script")
    public ScriptsGetByUserIdResponse handle() {
        FindScriptsByUserIdQuery query = new FindScriptsByUserIdQuery("1", 0, 10);
        FindScriptsByUserIdResponse findScriptsByUserIdResponse = ask(query);
        return new ScriptsGetByUserIdResponse(findScriptsByUserIdResponse.scriptsResponses);
    }
}

class ScriptsGetByUserIdResponse {
    public List<ScriptGetByUserIdResponse> scriptsResponses;

    public ScriptsGetByUserIdResponse(List<ScriptResponse> scriptResponses) {
        this.scriptsResponses = scriptResponses.stream().map(ScriptGetByUserIdResponse::new).toList();
    }
}

@AllArgsConstructor
@Getter
class ScriptGetByUserIdResponse {
    private String id;
    private String name;
    public ScriptGetByUserIdResponse(ScriptResponse scriptResponse) {
        this.id = scriptResponse.getId();
        this.name = scriptResponse.getName();
    }
}