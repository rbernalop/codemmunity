package org.rbernalop.servicechallengesynchronization.script.application.modify.content;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicechallengesynchronization.script.application.modify.ScriptModifier;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.content.ChangeChallengeScriptContentCommand;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class ChangeChallengeScriptContentCommandHandler implements CommandHandler<ChangeChallengeScriptContentCommand> {
    private final ScriptModifier scriptModifier;

    public ChangeChallengeScriptContentCommandHandler(QueryBus queryBus, EventBus eventBus, ChallengeScriptManager challengeScriptManager,
          ChallengeScriptRepository challengeScriptRepository) {
        this.scriptModifier = new ScriptModifier(queryBus, eventBus, challengeScriptManager, challengeScriptRepository);
    }

    @Override
    public void handle(ChangeChallengeScriptContentCommand command) {
        log.info("Changing challenge {} from user {} content", command.getChallengeId(), command.getUsername());

        ChallengeId id = new ChallengeId(command.getChallengeId());
        UserUsername username = new UserUsername(command.getUsername());
        ScriptContent content = new ScriptContent(command.getContent());
        scriptModifier.changeContent(id, username, content);
    }
}
