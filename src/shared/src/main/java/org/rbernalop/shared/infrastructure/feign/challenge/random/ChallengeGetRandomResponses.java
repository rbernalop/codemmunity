package org.rbernalop.shared.infrastructure.feign.challenge.random;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChallengeGetRandomResponses {
    private List<ChallengeGetRandomResponse> challenges;

    public static ChallengeGetRandomResponses from(List<ChallengeGetRandomResponse> challenges) {
        ChallengeGetRandomResponses responses = new ChallengeGetRandomResponses();
        responses.setChallenges(challenges);
        return responses;
    }
}
