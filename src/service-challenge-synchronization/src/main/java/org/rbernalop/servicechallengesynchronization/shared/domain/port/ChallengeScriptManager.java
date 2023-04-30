package org.rbernalop.servicechallengesynchronization.shared.domain.port;

import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

import java.util.Map;

public interface ChallengeScriptManager {
    ChallengeScript getScript(ChallengeScriptId id);
    void setScriptContent(ChallengeScriptId id, ScriptContent content);
    void setScriptLanguage(ChallengeScriptId id, LanguageName language);
    Map<ChallengeScriptId, ChallengeScript> getScriptsContent();
    void create(ChallengeScript challengeScript);
}
