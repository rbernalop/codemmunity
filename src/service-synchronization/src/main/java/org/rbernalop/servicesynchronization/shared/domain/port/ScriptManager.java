package org.rbernalop.servicesynchronization.shared.domain.port;

import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;
import java.util.Map;

public interface ScriptManager {
    ScriptContent getScriptContent(ScriptId id);
    void setScriptContent(ScriptId id, ScriptContent content);
    void addOnlineUser(ScriptId id, UserUsername username);
    void removeOnlineUser(ScriptId id, UserUsername username);
    List<UserUsername> getOnlineUsernames(ScriptId id);
    Map<ScriptId, ScriptContent> getScriptsContent();
}
