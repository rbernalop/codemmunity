package org.rbernalop.servicechallengesynchronization.script.infrastructure.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content.ChangeChallengeScriptContentCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChallengeScriptChangeContentSocket extends ApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChallengeScriptChangeContentSocket(QueryBus queryBus, CommandBus commandBus, SimpMessagingTemplate simpMessagingTemplate) {
        super(queryBus, commandBus);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/challengeScript/{challengeId}/changeContent")
    public void change(@Payload ChallengeScriptChangeContentSocketRequest scriptChangeSocketRequest, @DestinationVariable String challengeId) {
        ChangeChallengeScriptContentCommand command = new ChangeChallengeScriptContentCommand(
                challengeId,
                scriptChangeSocketRequest.getUsername(),
                scriptChangeSocketRequest.getChangedScript()
        );
        dispatch(command);

        ChallengeScriptChangeContentSocketResponse scriptChangeSocketResponse = new ChallengeScriptChangeContentSocketResponse();
        scriptChangeSocketResponse.setChallengeId(challengeId);
        scriptChangeSocketResponse.setUsername(scriptChangeSocketRequest.getUsername());
        scriptChangeSocketResponse.setChange(scriptChangeSocketRequest.getChange());
        scriptChangeSocketResponse.setChangedScript(scriptChangeSocketRequest.getChangedScript());
        simpMessagingTemplate.convertAndSend("/challengeScript/changedContent", scriptChangeSocketResponse);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ChallengeScriptChangeContentSocketRequest {
    private String username;
    private Object change;
    private String changedScript;
}

@Getter
@Setter
class ChallengeScriptChangeContentSocketResponse {
    private String challengeId;
    private String username;
    private Object change;
    private String changedScript;
}