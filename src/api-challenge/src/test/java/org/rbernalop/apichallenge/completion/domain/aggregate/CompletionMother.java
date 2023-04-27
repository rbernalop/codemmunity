package org.rbernalop.apichallenge.completion.domain.aggregate;

import org.rbernalop.apichallenge.completion.domain.value_object.CompletionIdMother;
import org.rbernalop.apichallenge.completion.domain.value_object.LanguageNameMother;
import org.rbernalop.apichallenge.completion.domain.value_object.ScriptContentMother;

public class CompletionMother {
    public static Completion random() {
        return Completion.create(
            CompletionIdMother.random(),
            LanguageNameMother.random(),
            ScriptContentMother.random()
        );
    }

    public static Completion fromChallengeIdAndUsername(String id, String requesterUsername) {
        return Completion.create(
            CompletionIdMother.fromChallengeIdAndUsername(id, requesterUsername),
            LanguageNameMother.random(),
            ScriptContentMother.random()
        );
    }
}