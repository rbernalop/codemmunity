package org.rbernalop.apichallenge.shared.application.challenge.finder.all;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.shared.application.challenge.finder.ChallengeResponse;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@Getter
@Setter
public class FindChallengesPaginatedResponse implements Response {
    private List<ChallengeResponse> challenges;
    private long totalChallenges;

    public static FindChallengesPaginatedResponse from(List<Challenge> challenges, long totalChallenges) {
        FindChallengesPaginatedResponse response = new FindChallengesPaginatedResponse();
        response.setChallenges(challenges.stream().map(ChallengeResponse::from).toList());
        response.setTotalChallenges(totalChallenges);
        return response;
    }
}
