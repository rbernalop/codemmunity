package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apiscript.shared.application.script.modify.RegenerateShareKeyCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ScriptPatchShareKeyController extends ApiController {
    public ScriptPatchShareKeyController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PatchMapping("script/{id}/shareKey")
    public void shareKey(@PathVariable String id, @RequestBody ScriptPatchShareKeyRequest request) {
        RegenerateShareKeyCommand command = new RegenerateShareKeyCommand(id, request.getShareKey());
        dispatch(command);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ScriptPatchShareKeyRequest {
    private String shareKey;
}
