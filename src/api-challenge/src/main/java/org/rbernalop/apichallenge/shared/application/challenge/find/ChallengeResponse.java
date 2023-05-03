package org.rbernalop.apichallenge.shared.application.challenge.find;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;

@Getter
@Setter
public class ChallengeResponse {
    private String id;
    private String title;
    private String description;
    private String category;
    private long difficulty;
    private String userUsername;
    private boolean isCompleted;

    public static ChallengeResponse from(Challenge challenge, boolean isCompleted) {
        ChallengeResponse challengeResponse = new ChallengeResponse();
        assert challenge.getId() != null;
        challengeResponse.setId(challenge.getId().getValue());
        challengeResponse.setTitle(challenge.getTitle());
        challengeResponse.setDescription(challenge.getDescription());
        challengeResponse.setCategory(challenge.getCategory());
        challengeResponse.setDifficulty(challenge.getDifficulty());
        challengeResponse.setUserUsername(challenge.getUserUsername());
        challengeResponse.setCompleted(isCompleted);
        return challengeResponse;
    }
}
