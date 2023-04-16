package org.rbernalop.servicesynchronization.script.application.create;

import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;

public class ScriptCreator extends UseCase {
    private final ScriptRepository scriptRepository;
    private final ScriptManager scriptManager;

    public ScriptCreator(ScriptRepository scriptRepository, QueryBus queryBus, ScriptManager scriptManager) {
        super(queryBus);
        this.scriptRepository = scriptRepository;
        this.scriptManager = scriptManager;
    }


    public void create(ScriptId id, ScriptContent content) {
        scriptManager.setScriptContent(id, content);

        Script script = Script.create(id, content);
        scriptRepository.save(script);
    }
}
