package org.rbernalop.servicesynchronization.script.application.modify;

import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;

public class ScriptModifier extends UseCase {
    private final ScriptManager scriptManager;

    public ScriptModifier(QueryBus queryBus, EventBus eventBus, ScriptManager scriptManager) {
        super(queryBus, eventBus);
        this.scriptManager = scriptManager;
    }

    public void changeContent(ScriptId id, ScriptContent content) {
        scriptManager.setScriptContent(id, content);
    }
}
