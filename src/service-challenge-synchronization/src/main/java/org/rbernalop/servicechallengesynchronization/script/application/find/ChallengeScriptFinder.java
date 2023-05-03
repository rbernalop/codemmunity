package org.rbernalop.servicechallengesynchronization.script.application.find;


import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindScriptByChallengeIdResponse;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;

import java.util.Optional;

public class ChallengeScriptFinder extends UseCase {
    private final ChallengeScriptRepository challengeScriptRepository;
    private final ChallengeScriptManager challengeScriptManager;

    public ChallengeScriptFinder(QueryBus queryBus, EventBus eventBus,
         ChallengeScriptRepository challengeScriptRepository, ChallengeScriptManager challengeScriptManager) {
        super(queryBus, eventBus);
        this.challengeScriptRepository = challengeScriptRepository;
        this.challengeScriptManager = challengeScriptManager;
    }


    public FindScriptByChallengeIdResponse findById(ChallengeScriptId id) {
        ChallengeScript challengeScript = challengeScriptManager.getScript(id);
        if (challengeScript != null) {
            return FindScriptByChallengeIdResponse.from(challengeScript);
        }

        Optional<ChallengeScript> optionalChallengeScript = challengeScriptRepository.findById(id);
        if (optionalChallengeScript.isPresent()) {
            challengeScript = optionalChallengeScript.get();
            challengeScriptManager.create(challengeScript);
            return FindScriptByChallengeIdResponse.from(challengeScript);
        }

        return null;
    }
}
