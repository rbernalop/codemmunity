package org.rbernalop.servicechallengesynchronization.script.application.modify;

import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class ScriptModifier extends UseCase {
    private final ChallengeScriptManager challengeScriptManager;
    private final ChallengeScriptRepository challengeScriptRepository;


    public ScriptModifier(QueryBus queryBus, EventBus eventBus, ChallengeScriptManager challengeScriptManager,
          ChallengeScriptRepository challengeScriptRepository) {
        super(queryBus, eventBus);
        this.challengeScriptManager = challengeScriptManager;
        this.challengeScriptRepository = challengeScriptRepository;
    }

    private ChallengeScriptId getChallengeScriptId(ChallengeId id, UserUsername username) {
        ChallengeScriptId scriptId = new ChallengeScriptId(id, username);

        ChallengeScript challengeScript = challengeScriptManager.getScript(scriptId);
        if (challengeScript != null) {
            return scriptId;
        }

        challengeScript = challengeScriptRepository.findById(scriptId).orElse(null);
        if (challengeScript == null) {
            return null;
        }

        challengeScriptManager.create(challengeScript);
        return scriptId;
    }

    public void changeContent(ChallengeId id, UserUsername username, ScriptContent content) {
        ChallengeScriptId scriptId = getChallengeScriptId(id, username);
        if (scriptId == null) {
            return;
        }
        challengeScriptManager.setScriptContent(scriptId, content);
    }

    public void changeLanguage(ChallengeId id, UserUsername username, LanguageName languageName) {
        ChallengeScriptId scriptId = getChallengeScriptId(id, username);
        if (scriptId == null) {
            return;
        }
        challengeScriptManager.setScriptLanguage(scriptId, languageName);
    }
}
