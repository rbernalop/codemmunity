package org.rbernalop.servicechallengesynchronization.script.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.application.script.create.CreateChallengeScriptCommand;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class CreateChallengeScriptCommandHandler implements CommandHandler<CreateChallengeScriptCommand> {
    private final ChallengeScriptCreator challengeScriptCreator;

    public CreateChallengeScriptCommandHandler(QueryBus queryBus, EventBus eventBus,
            ChallengeScriptManager challengeScriptManager, ChallengeScriptRepository challengeScriptRepository) {
        this.challengeScriptCreator = new ChallengeScriptCreator(queryBus, eventBus, challengeScriptManager, challengeScriptRepository);
    }

    @Override
    public void handle(CreateChallengeScriptCommand command) {
        log.info("Creating challenge {} script in language {} from user {}", command.getChallengeId(),
                command.getLanguageName(), command.getUsername());

        ChallengeId id = new ChallengeId(command.getChallengeId());
        UserUsername username = new UserUsername(command.getUsername());
        ChallengeScriptId challengeScriptId = new ChallengeScriptId(id, username);
        LanguageName languageName = new LanguageName(command.getLanguageName());
        ScriptContent content = new ScriptContent(command.getContent());
        challengeScriptCreator.create(challengeScriptId, content, languageName);
    }
}
