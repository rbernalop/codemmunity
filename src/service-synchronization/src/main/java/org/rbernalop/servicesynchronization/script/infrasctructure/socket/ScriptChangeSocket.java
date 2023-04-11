package org.rbernalop.servicesynchronization.script.infrasctructure.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicesynchronization.shared.application.script.modify.ChangeScriptContentCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ScriptChangeSocket extends ApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ScriptChangeSocket(QueryBus queryBus, CommandBus commandBus, SimpMessagingTemplate simpMessagingTemplate) {
        super(queryBus, commandBus);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/script/{id}/change")
    public void change(@Payload ScriptChangeSocketRequest scriptChangeSocketRequest, @DestinationVariable String id) {
        ChangeScriptContentCommand changeScriptContentCommand = new ChangeScriptContentCommand(id, scriptChangeSocketRequest.getChangedScript());
        dispatch(changeScriptContentCommand);

        ScriptChangeSocketResponse scriptChangeSocketResponse = new ScriptChangeSocketResponse();
        scriptChangeSocketResponse.setScriptId(id);
        scriptChangeSocketResponse.setUsername(scriptChangeSocketRequest.getUsername());
        scriptChangeSocketResponse.setChange(scriptChangeSocketRequest.getChange());
        simpMessagingTemplate.convertAndSend("/script/changed", scriptChangeSocketResponse);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ScriptChangeSocketRequest {
    private String username;
    private Object change;
    private String changedScript;
}

@Getter
@Setter
class ScriptChangeSocketResponse {
    private String scriptId;
    private String username;
    private Object change;
}