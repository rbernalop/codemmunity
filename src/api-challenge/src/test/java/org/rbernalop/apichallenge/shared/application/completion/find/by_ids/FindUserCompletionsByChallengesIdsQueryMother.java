package org.rbernalop.apichallenge.shared.application.completion.find.by_ids;

import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;

import java.util.List;

public class FindUserCompletionsByChallengesIdsQueryMother {
    public static FindUserCompletionsByIdsQuery fromCompletionId(CompletionId completionId) {
        return new FindUserCompletionsByIdsQuery(
            List.of(completionId.getChallengeId()),
            completionId.getUserUsername()
        );
    }
}