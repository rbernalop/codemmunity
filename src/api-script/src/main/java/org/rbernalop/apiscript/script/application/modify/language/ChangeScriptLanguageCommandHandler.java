package org.rbernalop.apiscript.script.application.modify.language;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.modify.ScriptModifier;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.apiscript.shared.application.script.modify.ChangeScriptLanguageCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;

@Service
@Slf4j
public class ChangeScriptLanguageCommandHandler  implements CommandHandler<ChangeScriptLanguageCommand> {
    private final ScriptModifier scriptModifier;

    public ChangeScriptLanguageCommandHandler(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository) {
        this.scriptModifier = new ScriptModifier(queryBus, eventBus, scriptRepository);
    }

    @Override
    public void handle(ChangeScriptLanguageCommand command) {
        log.info("Changing language of script with id: {}, to: {}", command.getId(), command.getLanguageId());
        ScriptId id = new ScriptId(command.getId());
        LanguageId languageId = new LanguageId(command.getLanguageId());
        scriptModifier.changeScriptLanguage(id, languageId);
    }
}
