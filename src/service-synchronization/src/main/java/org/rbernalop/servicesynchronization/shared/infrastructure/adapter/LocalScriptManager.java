package org.rbernalop.servicesynchronization.shared.infrastructure.adapter;

import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class LocalScriptManager implements ScriptManager {
    private Map<ScriptId, ScriptContent> scripts = new HashMap<>();
    private Map<ScriptId, List<UserUsername>> onlineUsers = new HashMap<>();


    @Override
    public ScriptContent getScriptContent(ScriptId id)  {
        return scripts.get(id);
    }

    @Override
    public void setScriptContent(ScriptId id, ScriptContent content) {
        scripts.put(id, content);
    }

    @Override
    public void addOnlineUser(ScriptId id, UserUsername username) {
        List<UserUsername> users = onlineUsers.get(id);
        if (users == null) {
            users =  new ArrayList<>(List.of(username));
        } else{
            users.add(username);
        }
        onlineUsers.put(id, users);
    }

    @Override
    public void removeOnlineUser(ScriptId id, UserUsername username) {
        List<UserUsername> users = onlineUsers.get(id);
        if (users == null) {
            return;
        }
        users.remove(username);
        onlineUsers.put(id, users);
    }

    @Override
    public List<UserUsername> getOnlineUsernames(ScriptId id) {
        return onlineUsers.get(id);
    }
}
