package org.rbernalop.servicesynchronization.script.application.create;

import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.ShareKey;

public class ScriptCreator extends UseCase {
    private final ScriptRepository scriptRepository;
    private final ScriptManager scriptManager;

    public ScriptCreator(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository, ScriptManager scriptManager) {
        super(queryBus, eventBus);
        this.scriptRepository = scriptRepository;
        this.scriptManager = scriptManager;
    }


    public void create(ScriptId id, ShareKey shareKey, ScriptContent content) {
        scriptManager.setScriptContent(id, content);

        Script script = Script.create(id, shareKey, content);
        scriptRepository.save(script);
    }
}
