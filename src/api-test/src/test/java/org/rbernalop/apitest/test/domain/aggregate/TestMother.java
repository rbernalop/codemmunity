package org.rbernalop.apitest.test.domain.aggregate;

import org.rbernalop.apitest.test.domain.value_object.ScriptContentMother;
import org.rbernalop.apitest.test.domain.value_object.TestIdMother;
import org.rbernalop.apitest.test.domain.value_object.TestNameMother;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;

public class TestMother {
    public static Test fromChallengeIdAndLanguageName(String challengeId, String languageName) {
        return Test.create(TestIdMother.random(), TestNameMother.random(), new ChallengeId(challengeId),
                ScriptContentMother.random(), new LanguageName(languageName));
    }
}