package org.rbernalop.servicechallengesynchronization.shared.application.script.create;

import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;

public class CreateChallengeScriptCommandMother {
    public static CreateChallengeScriptCommand fromChallengeScript(ChallengeScript challengeScript) {
        return new CreateChallengeScriptCommand(challengeScript.getChallengeId(), challengeScript.getUserUsername(),
            challengeScript.getScriptContent(), challengeScript.getLanguageName());
    }
}