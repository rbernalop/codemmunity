package org.rbernalop.apiscript.script.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.ScriptName;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.apiscript.script.domain.value_object.ShareKey;
import org.rbernalop.apiscript.shared.application.script.create.CreateScriptCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class CreateScriptCommandHandler implements CommandHandler<CreateScriptCommand> {

    private final ScriptCreator scriptCreator;

    public CreateScriptCommandHandler(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository) {
        this.scriptCreator = new ScriptCreator(queryBus, eventBus, scriptRepository);
    }

    @Override
    public void handle(CreateScriptCommand command) {
        log.info("Creating script with id: {}, key: {}, languageId: {}, username: {}", command.getId(), command.getKey(), command.getLanguageId(), command.getUserUsername());
        ScriptId id = new ScriptId(command.getId());
        ScriptName name = new ScriptName("Untitled script");
        ShareKey key = new ShareKey(command.getKey());
        LanguageId languageId = new LanguageId(command.getLanguageId());
        UserUsername userUsername = new UserUsername(command.getUserUsername());
        scriptCreator.create(id, name, key, languageId, userUsername);
    }
}
