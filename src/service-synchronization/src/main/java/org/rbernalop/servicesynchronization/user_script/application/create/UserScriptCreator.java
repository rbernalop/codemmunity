package org.rbernalop.servicesynchronization.user_script.application.create;

import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class UserScriptCreator extends UseCase {
    private final ScriptManager scriptManager;

    public UserScriptCreator(ScriptManager scriptManager, QueryBus queryBus) {
        super(queryBus);
        this.scriptManager = scriptManager;
    }

    public void join(ScriptId scriptId, UserUsername username) {
        scriptManager.addOnlineUser(scriptId, username);
    }
}
