package org.rbernalop.servicechallengesynchronization.script.infrastructure.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindChallengeScriptByIdQuery;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindScriptByChallengeIdResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChallengeScriptJoinSocket extends ApiController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChallengeScriptJoinSocket(QueryBus queryBus, CommandBus commandBus, SimpMessagingTemplate simpMessagingTemplate) {
        super(queryBus, commandBus);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/challengeScript/{challengeId}/join")
    public void change(@Payload ChallengeScriptJoinSocketRequest scriptChangeSocketRequest, @DestinationVariable String challengeId) {
        FindChallengeScriptByIdQuery query = new FindChallengeScriptByIdQuery(challengeId, scriptChangeSocketRequest.getUsername());
        FindScriptByChallengeIdResponse response = ask(query);

        ChallengeScriptJoinSocketResponse scriptChangeSocketResponse = new ChallengeScriptJoinSocketResponse();
        scriptChangeSocketResponse.setChallengeId(challengeId);
        scriptChangeSocketResponse.setUsername(scriptChangeSocketRequest.getUsername());
        scriptChangeSocketResponse.setContent(response != null ? response.getContent() : null);
        scriptChangeSocketResponse.setLanguageName(response != null ? response.getLanguageName() : null);
        simpMessagingTemplate.convertAndSend("/challengeScript/joined", scriptChangeSocketResponse);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ChallengeScriptJoinSocketRequest {
    private String username;
}

@Getter
@Setter
class ChallengeScriptJoinSocketResponse {
    private String challengeId;
    private String username;
    private String content;
    private String languageName;
}