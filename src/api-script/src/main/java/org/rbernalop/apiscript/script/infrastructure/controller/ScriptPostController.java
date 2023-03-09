package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ScriptPostController extends ApiController {
    public ScriptPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("script")
    public void handle(@RequestBody ScriptPostRequest request, @RequestParam("user") String userName) {
        CreateScriptCommand command = new CreateScriptCommand(
            request.getId(),
            request.getKey(),
            request.getLanguageId(),
            userName
        );

        dispatch(command);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
class ScriptPostRequest {
    private String id;
    private String key;
    private String languageId;
}
