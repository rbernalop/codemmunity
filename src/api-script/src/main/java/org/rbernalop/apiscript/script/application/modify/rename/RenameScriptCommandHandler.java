package org.rbernalop.apiscript.script.application.modify.rename;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.modify.ScriptModifier;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.apiscript.script.domain.value_object.ScriptName;
import org.rbernalop.apiscript.shared.application.script.modify.RenameScriptCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class RenameScriptCommandHandler implements CommandHandler<RenameScriptCommand> {

    private final ScriptModifier scriptModifier;

    public RenameScriptCommandHandler(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository) {
        this.scriptModifier = new ScriptModifier(queryBus, eventBus, scriptRepository);
    }

    @Override
    public void handle(RenameScriptCommand command) {
        log.info("Renaming script by user: {}, with id: {}, to: {}", command.getUserUsername(), command.getId(), command.getNextName());
        ScriptId id = new ScriptId(command.getId());
        ScriptName nextName = new ScriptName(command.getNextName());
        UserUsername userUsername = new UserUsername(command.getUserUsername());
        scriptModifier.renameScript(id, nextName, userUsername);
    }
}
