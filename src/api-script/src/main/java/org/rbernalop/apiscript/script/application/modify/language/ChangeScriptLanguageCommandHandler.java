package org.rbernalop.apiscript.script.application.modify.language;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.modify.ScriptModifier;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.ScriptId;
import org.rbernalop.apiscript.script.domain.value_object.ScriptLanguageId;
import org.rbernalop.apiscript.shared.application.script.modify.ChangeScriptLanguageCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;

@Service
@Slf4j
public class ChangeScriptLanguageCommandHandler  implements CommandHandler<ChangeScriptLanguageCommand> {
    private final ScriptModifier scriptModifier;

    public ChangeScriptLanguageCommandHandler(QueryBus queryBus, ScriptRepository scriptRepository) {
        this.scriptModifier = new ScriptModifier(queryBus, scriptRepository);
    }

    @Override
    public void handle(ChangeScriptLanguageCommand command) {
        log.info("Changing language of script with id: {}, to: {}", command.getId(), command.getLanguageId());
        ScriptId id = new ScriptId(command.getId());
        ScriptLanguageId languageId = new ScriptLanguageId(command.getLanguageId());
        scriptModifier.changeScriptLanguage(id, languageId);
    }
}
