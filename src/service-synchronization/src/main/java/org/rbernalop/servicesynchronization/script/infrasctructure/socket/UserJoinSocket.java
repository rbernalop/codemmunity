package org.rbernalop.servicesynchronization.script.infrasctructure.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdQuery;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdResponse;
import org.rbernalop.servicesynchronization.shared.application.user_script.create.JoinUserScriptCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UserJoinSocket extends ApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public UserJoinSocket(QueryBus queryBus, CommandBus commandBus, SimpMessagingTemplate simpMessagingTemplate) {
        super(queryBus, commandBus);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/script/{id}/join")
    public void join(@Payload UserJoinSocketRequest userJoinSocketRequest, @DestinationVariable String id) {
        JoinUserScriptCommand joinUserScriptCommand = new JoinUserScriptCommand(userJoinSocketRequest.getUsername(), id);
        dispatch(joinUserScriptCommand);

        FindScriptByIdQuery findScriptByIdQuery = new FindScriptByIdQuery(id);
        FindScriptByIdResponse findScriptByIdResponse = ask(findScriptByIdQuery);

        UserJoinSocketResponse userJoinSocketResponse = new UserJoinSocketResponse();
        userJoinSocketResponse.setScriptId(id);
        userJoinSocketResponse.setUsername(userJoinSocketRequest.getUsername());
        userJoinSocketResponse.setScriptContent(findScriptByIdResponse.getContent());
        simpMessagingTemplate.convertAndSend("/script/joined", userJoinSocketResponse);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserJoinSocketRequest {
    private String username;
}

@Getter
@Setter
class UserJoinSocketResponse {
    private String scriptId;
    private String username;
    private String scriptContent;
}