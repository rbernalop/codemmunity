package org.rbernalop.servicechallengesynchronization.script.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class ChallengeScriptMother {
    public static ChallengeScript random() {
        ChallengeId challengeId = new ChallengeId(Faker.instance().internet().uuid());
        UserUsername username = new UserUsername(Faker.instance().name().username());
        ChallengeScriptId id = new ChallengeScriptId(challengeId, username);
        return ChallengeScript.create(id,
            new LanguageName(Faker.instance().name().username()),
            new ScriptContent(Faker.instance().lorem().paragraph())
        );
    }
}