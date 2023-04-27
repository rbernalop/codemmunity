package org.rbernalop.apichallenge.completion.domain.value_object;

import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeIdMother;
import org.rbernalop.apichallenge.challenge.domain.value_object.UserUsernameMother;

public class CompletionIdMother {
    public static CompletionId random() {
        return new CompletionId(
            ChallengeIdMother.random(),
            UserUsernameMother.random()
        );
    }

    public static CompletionId fromChallengeIdAndUsername(String id, String requesterUsername) {
        return new CompletionId(
            ChallengeIdMother.fromId(id),
            UserUsernameMother.fromUsername(requesterUsername)
        );
    }
}
