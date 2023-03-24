package org.rbernalop.apiscript.script.application.modify.share_key;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.modify.ScriptModifier;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.ScriptId;
import org.rbernalop.apiscript.script.domain.value_object.ShareKey;
import org.rbernalop.apiscript.shared.application.script.modify.RegenerateShareKeyCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.query.QueryBus;

@Service
@Slf4j
public class RegenerateShareKeyCommandHandler implements CommandHandler<RegenerateShareKeyCommand> {
    private final ScriptModifier scriptModifier;
    public RegenerateShareKeyCommandHandler(QueryBus queryBus, ScriptRepository scriptRepository) {
        this.scriptModifier = new ScriptModifier(queryBus, scriptRepository);
    }

    @Override
    public void handle(RegenerateShareKeyCommand command) {
        log.info("Regenerating share key for script with id: {}", command.getId());
        ScriptId id = new ScriptId(command.getId());
        ShareKey shareKey = new ShareKey(command.getShareKey());
        scriptModifier.regenerateShareKey(id, shareKey);
    }
}
