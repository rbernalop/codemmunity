package org.rbernalop.servicechallengesynchronization.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.language.ChangeChallengeScriptLanguageCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChallengeScriptChangeLanguageSocket extends ApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChallengeScriptChangeLanguageSocket(QueryBus queryBus, CommandBus commandBus, SimpMessagingTemplate simpMessagingTemplate) {
        super(queryBus, commandBus);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/challengeScript/{challengeId}/changeLanguage")
    public void change(@Payload ChallengeScriptChangeLanguageSocketRequest scriptChangeSocketRequest, @DestinationVariable String challengeId) {
        ChangeChallengeScriptLanguageCommand command = new ChangeChallengeScriptLanguageCommand(
                challengeId,
                scriptChangeSocketRequest.getUsername(),
                scriptChangeSocketRequest.getLanguage()
        );
        dispatch(command);

        ChallengeScriptChangeLanguageSocketResponse scriptChangeSocketResponse = new ChallengeScriptChangeLanguageSocketResponse();
        scriptChangeSocketResponse.setChallengeId(challengeId);
        scriptChangeSocketResponse.setUsername(scriptChangeSocketRequest.getUsername());
        scriptChangeSocketResponse.setLanguage(scriptChangeSocketRequest.getLanguage());
        simpMessagingTemplate.convertAndSend("/challengeScript/changedLanguage", scriptChangeSocketResponse);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ChallengeScriptChangeLanguageSocketRequest {
    private String username;
    private String language;
}

@Getter
@Setter
class ChallengeScriptChangeLanguageSocketResponse {
    private String challengeId;
    private String username;
    private String language;
}