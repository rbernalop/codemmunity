package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apiscript.shared.application.script.modify.RenameScriptCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ScriptPatchController extends ApiController {

    public ScriptPatchController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PatchMapping("script/{id}")
    public void handle(@RequestBody ScriptPatchRequest request,
                       @PathVariable("id") String id,
                       @RequestParam("user") String userName) {
        RenameScriptCommand command = new RenameScriptCommand(id, request.getName(), userName);
        dispatch(command);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ScriptPatchRequest {
    private String name;
}