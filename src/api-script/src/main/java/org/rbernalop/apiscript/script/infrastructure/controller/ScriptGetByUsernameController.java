package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.shared.application.script.find.by_userid.FindScriptsByUsernameQuery;
import org.rbernalop.apiscript.shared.application.script.find.by_userid.FindScriptsByUsernameResponse;
import org.rbernalop.apiscript.shared.application.script.find.ScriptResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ScriptGetByUsernameController extends ApiController {

    public ScriptGetByUsernameController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("script")
    public ScriptsGetByUsernameResponse handle(@RequestParam("user") String userName,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        FindScriptsByUsernameQuery query = new FindScriptsByUsernameQuery(userName, page, size);
        FindScriptsByUsernameResponse findScriptsByUsernameResponse = ask(query);
        return new ScriptsGetByUsernameResponse(findScriptsByUsernameResponse.scriptsResponses);
    }
}


@NoArgsConstructor
@Getter
class ScriptsGetByUsernameResponse {
    public List<ScriptGetByUsernameResponse> scriptsResponses;

    public ScriptsGetByUsernameResponse(List<ScriptResponse> scriptResponses) {
        this.scriptsResponses = scriptResponses.stream().map(ScriptGetByUsernameResponse::new).toList();
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class ScriptGetByUsernameResponse {
    private String id;
    private String name;
    public ScriptGetByUsernameResponse(ScriptResponse scriptResponse) {
        this.id = scriptResponse.getId();
        this.name = scriptResponse.getName();
    }
}