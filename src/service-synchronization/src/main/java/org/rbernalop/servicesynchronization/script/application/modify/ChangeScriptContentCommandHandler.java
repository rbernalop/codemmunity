package org.rbernalop.servicesynchronization.script.application.modify;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.shared.application.script.modify.ChangeScriptContentCommand;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;

@Service
@Slf4j
public class ChangeScriptContentCommandHandler implements CommandHandler<ChangeScriptContentCommand> {
    private final ScriptModifier scriptModifier;

    public ChangeScriptContentCommandHandler(QueryBus queryBus, EventBus eventBus, ScriptManager scriptManager) {
        this.scriptModifier = new ScriptModifier(queryBus, eventBus, scriptManager);
    }

    @Override
    public void handle(ChangeScriptContentCommand command) {
        log.info("Changing script {} content", command.getId());

        ScriptId id = new ScriptId(command.getId());
        ScriptContent content = new ScriptContent(command.getContent());
        scriptModifier.changeContent(id, content);
    }
}
