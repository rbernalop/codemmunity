package org.rbernalop.servicechallengesynchronization.script.application.create;

import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

public class ChallengeScriptCreator extends UseCase {
    private final ChallengeScriptManager challengeScriptManager;
    private final ChallengeScriptRepository challengeScriptRepository;

    public ChallengeScriptCreator(QueryBus queryBus, EventBus eventBus, ChallengeScriptManager challengeScriptManager,
            ChallengeScriptRepository challengeScriptRepository) {
        super(queryBus, eventBus);
        this.challengeScriptManager = challengeScriptManager;
        this.challengeScriptRepository = challengeScriptRepository;
    }

    public void create(ChallengeScriptId id, ScriptContent content, LanguageName languageName) {
        ChallengeScript challengeScript = ChallengeScript.create(id, languageName, content);
        challengeScriptRepository.save(challengeScript);
        challengeScriptManager.create(challengeScript);
    }
}
