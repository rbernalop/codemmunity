package org.rbernalop.servicesynchronization.user_script.application.find;

import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdResponse;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

public class UserScriptFinder extends UseCase {
    private final ScriptManager scriptManager;

    public UserScriptFinder(ScriptManager scriptManager, QueryBus queryBus) {
        super(queryBus);
        this.scriptManager = scriptManager;
    }


    public FindUsersByScriptIdResponse findUsersByScriptId(ScriptId scriptId) {
        List<UserUsername> usernames = scriptManager.getOnlineUsernames(scriptId);
        return FindUsersByScriptIdResponse.fromUsernames(usernames);
    }
}
