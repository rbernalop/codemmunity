package org.rbernalop.apichallenge.baseCode.domain.aggregate;

import com.github.javafaker.Faker;
import org.rbernalop.apichallenge.baseCode.domain.value_object.BaseCodeId;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

public class BaseCodeMother {
    public static BaseCode random() {
        ChallengeId challengeId = new ChallengeId(Faker.instance().internet().uuid());
        LanguageName languageName = new LanguageName(Faker.instance().name().name());
        BaseCodeId id = new BaseCodeId(challengeId, languageName);
        return new BaseCode(id, new ScriptContent(Faker.instance().lorem().paragraph()));
    }
}