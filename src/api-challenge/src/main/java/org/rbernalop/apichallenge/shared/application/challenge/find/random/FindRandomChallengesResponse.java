package org.rbernalop.apichallenge.shared.application.challenge.find.random;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.shared.application.challenge.find.ChallengeResponse;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@Getter
@Setter
public class FindRandomChallengesResponse implements Response {
    private List<ChallengeResponse> challenges;

    public static FindRandomChallengesResponse from(List<Challenge> challenges) {
        FindRandomChallengesResponse response = new FindRandomChallengesResponse();
        response.setChallenges(challenges.stream().map(ChallengeResponse::from).toList());
        return response;
    }
}
