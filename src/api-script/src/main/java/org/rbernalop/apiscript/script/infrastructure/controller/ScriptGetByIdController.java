package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ScriptGetByIdController extends ApiController {
    public ScriptGetByIdController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("script/{id}")
    public ScriptGetByIdResponse handle(@PathVariable("id") String id) {
        FindScriptsByIdQuery query = new FindScriptsByIdQuery(id);
        FindScriptsByIdResponse response = ask(query);
        return new ScriptGetByIdResponse(response);
    }
}

@NoArgsConstructor
@Getter
class ScriptGetByIdResponse {
    private String id;
    private String name;
    private String languageId;
    private String shareKey;

    public ScriptGetByIdResponse(FindScriptsByIdResponse response) {
        this.id = response.getId();
        this.name = response.getName();
        this.languageId = response.getLanguageId();
        this.shareKey = response.getShareKey();
    }
}
