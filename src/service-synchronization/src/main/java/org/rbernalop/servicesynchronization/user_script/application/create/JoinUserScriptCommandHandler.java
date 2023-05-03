package org.rbernalop.servicesynchronization.user_script.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.shared.application.user_script.create.JoinUserScriptCommand;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class JoinUserScriptCommandHandler implements CommandHandler<JoinUserScriptCommand> {

    private final UserScriptCreator userScriptCreator;

    public JoinUserScriptCommandHandler(QueryBus queryBus, EventBus eventBus, ScriptManager scriptManager) {
        this.userScriptCreator = new UserScriptCreator(queryBus, eventBus, scriptManager);
    }

    @Override
    public void handle(JoinUserScriptCommand command) {
        log.info("Joining user {} to script {}", command.getUsername(), command.getScriptId());
        ScriptId scriptId = new ScriptId(command.getScriptId());
        UserUsername username = new UserUsername(command.getUsername());
        userScriptCreator.join(scriptId, username);
    }
}
