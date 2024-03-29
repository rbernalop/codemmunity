package org.rbernalop.servicesynchronization.user_script.application.delete;

import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class UserScriptRemover extends UseCase {
    private final ScriptManager scriptManager;
    public UserScriptRemover(QueryBus queryBus, EventBus eventBus, ScriptManager scriptManager) {
        super(queryBus, eventBus);
        this.scriptManager = scriptManager;
    }

    public void leave(ScriptId scriptId, UserUsername username) {
        scriptManager.removeOnlineUser(scriptId, username);
    }
}
