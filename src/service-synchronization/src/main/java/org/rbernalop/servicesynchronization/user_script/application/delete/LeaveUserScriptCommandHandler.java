package org.rbernalop.servicesynchronization.user_script.application.delete;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.shared.application.user_script.delete.LeaveUserScriptCommand;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class LeaveUserScriptCommandHandler implements CommandHandler<LeaveUserScriptCommand> {
    private final UserScriptRemover userScriptRemover;

    public LeaveUserScriptCommandHandler(QueryBus queryBus, EventBus eventBus, ScriptManager scriptManager) {
        this.userScriptRemover = new UserScriptRemover(queryBus, eventBus, scriptManager);
    }

    @Override
    public void handle(LeaveUserScriptCommand command) {
        log.info("User {} left script {}", command.getUsername(), command.getScriptId());
        ScriptId scriptId = new ScriptId(command.getScriptId());
        UserUsername username = new UserUsername(command.getUsername());
        userScriptRemover.leave(scriptId, username);
    }
}
