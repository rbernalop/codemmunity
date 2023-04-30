package org.rbernalop.servicechallengesynchronization.script.application.modify;

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

    public ScriptModifier(QueryBus queryBus, EventBus eventBus, ChallengeScriptManager challengeScriptManager) {
        super(queryBus, eventBus);
        this.challengeScriptManager = challengeScriptManager;
    }

    public void changeContent(ChallengeId id, UserUsername username, ScriptContent content) {
        ChallengeScriptId scriptId = new ChallengeScriptId(id, username);
        challengeScriptManager.setScriptContent(scriptId, content);
    }

    public void changeLanguage(ChallengeId id, UserUsername username, LanguageName languageName) {
        ChallengeScriptId scriptId = new ChallengeScriptId(id, username);
        challengeScriptManager.setScriptLanguage(scriptId, languageName);
    }
}
