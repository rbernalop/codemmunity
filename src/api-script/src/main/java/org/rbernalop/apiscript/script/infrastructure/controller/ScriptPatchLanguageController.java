package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apiscript.shared.application.script.modify.ChangeScriptLanguageCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ScriptPatchLanguageController extends ApiController {
    public ScriptPatchLanguageController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PatchMapping("script/{id}/language")
    public void handle(@PathVariable String id, @RequestBody ScriptPatchLanguageRequest request) {
        ChangeScriptLanguageCommand command = new ChangeScriptLanguageCommand(id, request.getLanguageId());
        dispatch(command);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ScriptPatchLanguageRequest {
    private String languageId;
}