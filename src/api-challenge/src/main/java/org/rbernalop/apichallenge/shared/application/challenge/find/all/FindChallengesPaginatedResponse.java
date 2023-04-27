package org.rbernalop.apichallenge.shared.application.challenge.find.all;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.shared.application.challenge.find.ChallengeResponse;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.CompletionResponse;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FindChallengesPaginatedResponse implements Response {
    private List<ChallengeResponse> challenges;
    private long totalChallenges;

    public static FindChallengesPaginatedResponse from(List<Challenge> challenges, Map<String, CompletionResponse> userCompletionMap, long totalChallenges) {
        FindChallengesPaginatedResponse response = new FindChallengesPaginatedResponse();
        response.setChallenges(challenges.stream().map(challenge ->
            ChallengeResponse.from(challenge, userCompletionMap.containsKey(challenge.getId().getValue()))
        ).toList());
        response.setTotalChallenges(totalChallenges);
        return response;
    }
}
