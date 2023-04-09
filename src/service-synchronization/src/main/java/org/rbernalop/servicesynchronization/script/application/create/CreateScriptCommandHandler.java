package org.rbernalop.servicesynchronization.script.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;

@Service
@Slf4j
public class CreateScriptCommandHandler implements CommandHandler<CreateScriptCommand> {

    private final ScriptCreator scriptCreator;

    public CreateScriptCommandHandler(ScriptRepository scriptRepository, QueryBus queryBus, ScriptManager scriptManager) {
        this.scriptCreator = new ScriptCreator(scriptRepository, queryBus, scriptManager);
    }

    @Override
    public void handle(CreateScriptCommand command) {
        log.info("Creating script with id: {}", command.getId());
        ScriptId id = new ScriptId(command.getId());
        ScriptContent content = new ScriptContent("");
        scriptCreator.create(id, content);
    }
}
