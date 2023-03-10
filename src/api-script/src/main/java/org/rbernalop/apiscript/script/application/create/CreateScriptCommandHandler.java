package org.rbernalop.apiscript.script.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.OwnerUsername;
import org.rbernalop.apiscript.script.domain.value_object.ScriptId;
import org.rbernalop.apiscript.script.domain.value_object.ScriptLanguageId;
import org.rbernalop.apiscript.script.domain.value_object.ShareKey;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;

@Service
@Slf4j
public class CreateScriptCommandHandler implements CommandHandler<CreateScriptCommand> {

    private final ScriptCreator scriptCreator;

    public CreateScriptCommandHandler(QueryBus queryBus, ScriptRepository scriptRepository) {
        this.scriptCreator = new ScriptCreator(queryBus, scriptRepository);
    }

    @Override
    public void handle(CreateScriptCommand command) {
        log.info("Creating script with id: {}, key: {}, languageId: {}, ownerUserName: {}", command.getId(), command.getKey(), command.getLanguageId(), command.getOwnerUserName());
        ScriptId id = new ScriptId(command.getId());
        ShareKey key = new ShareKey(command.getKey());
        ScriptLanguageId languageId = new ScriptLanguageId(command.getLanguageId());
        OwnerUsername ownerUserName = new OwnerUsername(command.getOwnerUserName());
        scriptCreator.create(id, key, languageId, ownerUserName);
    }
}
