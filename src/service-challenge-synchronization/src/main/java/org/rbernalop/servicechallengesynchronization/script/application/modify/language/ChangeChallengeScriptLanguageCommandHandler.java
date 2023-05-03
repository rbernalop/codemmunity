package org.rbernalop.servicechallengesynchronization.script.application.modify.language;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicechallengesynchronization.script.application.modify.ScriptModifier;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.shared.application.script.modify.language.ChangeChallengeScriptLanguageCommand;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class ChangeChallengeScriptLanguageCommandHandler implements CommandHandler<ChangeChallengeScriptLanguageCommand> {
    private final ScriptModifier scriptModifier;

    public ChangeChallengeScriptLanguageCommandHandler(QueryBus queryBus, EventBus eventBus, ChallengeScriptManager challengeScriptManager,
           ChallengeScriptRepository challengeScriptRepository) {
        this.scriptModifier = new ScriptModifier(queryBus, eventBus, challengeScriptManager, challengeScriptRepository);
    }

    @Override
    public void handle(ChangeChallengeScriptLanguageCommand command) {
        log.info("Changing challenge {} script from user {} language", command.getChallengeId(), command.getUsername());

        ChallengeId id = new ChallengeId(command.getChallengeId());
        UserUsername username = new UserUsername(command.getUsername());
        LanguageName languageName = new LanguageName(command.getLanguageName());
        scriptModifier.changeLanguage(id, username, languageName);
    }
}
