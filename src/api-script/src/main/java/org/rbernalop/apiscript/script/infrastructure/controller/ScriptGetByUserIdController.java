package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdResponse;
import org.rbernalop.apiscript.shared.application.script.find.ScriptResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ScriptGetByUserIdController extends ApiController {

    public ScriptGetByUserIdController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("script")
    public ScriptsGetByUserIdResponse handle(@RequestBody ScriptsGetByUserIdRequest request) {
        FindScriptsByUserIdQuery query = new FindScriptsByUserIdQuery(request.getUserId(), request.getPage(), request.getSize());
        FindScriptsByUserIdResponse findScriptsByUserIdResponse = ask(query);
        return new ScriptsGetByUserIdResponse(findScriptsByUserIdResponse.scriptsResponses);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ScriptsGetByUserIdRequest {
    private int page;
    private int size;
    private String userId;
}


@NoArgsConstructor
@Getter
class ScriptsGetByUserIdResponse {
    public List<ScriptGetByUserIdResponse> scriptsResponses;

    public ScriptsGetByUserIdResponse(List<ScriptResponse> scriptResponses) {
        this.scriptsResponses = scriptResponses.stream().map(ScriptGetByUserIdResponse::new).toList();
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class ScriptGetByUserIdResponse {
    private String id;
    private String name;
    public ScriptGetByUserIdResponse(ScriptResponse scriptResponse) {
        this.id = scriptResponse.getId();
        this.name = scriptResponse.getName();
    }
}