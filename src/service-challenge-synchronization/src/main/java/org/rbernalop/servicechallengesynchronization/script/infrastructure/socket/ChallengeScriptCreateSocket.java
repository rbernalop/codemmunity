package org.rbernalop.servicechallengesynchronization.script.infrastructure.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicechallengesynchronization.shared.application.script.create.CreateChallengeScriptCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class ChallengeScriptCreateSocket extends ApiController {

    public ChallengeScriptCreateSocket(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @MessageMapping("/challengeScript/{challengeId}/create")
    public void create(@DestinationVariable String challengeId, @Payload ChallengeScriptCreateSocketRequest scriptCreateSocketRequest) {
        CreateChallengeScriptCommand command = new CreateChallengeScriptCommand(
            challengeId,
            scriptCreateSocketRequest.getUsername(),
            scriptCreateSocketRequest.getContent(),
            scriptCreateSocketRequest.getLanguageName()
        );
        dispatch(command);
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ChallengeScriptCreateSocketRequest {
    private String username;
    private String content;
    private String languageName;
}
