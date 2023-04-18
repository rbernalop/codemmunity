package org.rbernalop.apiscript.script.application.create;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.ScriptName;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ShareKey;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class ScriptCreator extends UseCase {

    private final ScriptRepository scriptRepository;

    public ScriptCreator(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository) {
        super(queryBus, eventBus);
        this.scriptRepository = scriptRepository;
    }

    public void create(ScriptId id, ScriptName name, ShareKey key, LanguageId languageId, UserUsername userUsername) {
        Script script = Script.create(id, userUsername, name, key, languageId);
        scriptRepository.save(script);
        this.eventBus.publish(script.pullDomainEvents());
    }

}
