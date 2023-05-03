package org.rbernalop.servicesynchronization.script.infrasctructure.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicesynchronization.shared.application.user_script.delete.LeaveUserScriptCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UserLeftSocket extends ApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public UserLeftSocket(QueryBus queryBus, CommandBus commandBus, SimpMessagingTemplate simpMessagingTemplate) {
        super(queryBus, commandBus);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/script/{id}/leave")
    public void join(@Payload UserLeftSocketRequest userJoinSocketRequest, @DestinationVariable String id) {
        LeaveUserScriptCommand leaveUserScriptCommand = new LeaveUserScriptCommand(userJoinSocketRequest.getUsername(), id);
        dispatch(leaveUserScriptCommand);

        UserLeftSocketResponse userJoinSocketResponse = new UserLeftSocketResponse();
        userJoinSocketResponse.setScriptId(id);
        userJoinSocketResponse.setUsername(userJoinSocketRequest.getUsername());
        simpMessagingTemplate.convertAndSend("/script/left", userJoinSocketResponse);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserLeftSocketRequest {
    private String username;
}

@Getter
@Setter
class UserLeftSocketResponse {
    private String scriptId;
    private String username;
}