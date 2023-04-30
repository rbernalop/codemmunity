package org.rbernalop.servicechallengesynchronization.shared.infrastructure.adapter;

import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LocalChallengeScriptManager implements ChallengeScriptManager {
    private Map<ChallengeScriptId, ChallengeScript> scripts = new HashMap<>();

    @Override
    public ChallengeScript getScript(ChallengeScriptId id)  {
        return scripts.get(id);
    }

    @Override
    public void setScriptContent(ChallengeScriptId id, ScriptContent content) {
        ChallengeScript script = scripts.get(id);
        script.updateContent(content);
        scripts.put(id, script);
    }

    @Override
    public void setScriptLanguage(ChallengeScriptId id, LanguageName language) {
        ChallengeScript script = scripts.get(id);
        script.updateLanguageName(language);
        scripts.put(id, script);
    }

    @Override
    public Map<ChallengeScriptId, ChallengeScript> getScriptsContent() {
        return scripts;
    }

    @Override
    public void create(ChallengeScript challengeScript) {
        scripts.put(challengeScript.getChallengeScriptId(), challengeScript);
    }
}
