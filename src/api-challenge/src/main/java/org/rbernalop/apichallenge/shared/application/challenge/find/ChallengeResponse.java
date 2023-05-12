package org.rbernalop.apichallenge.shared.application.challenge.find;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids.FindBaseCodeByChallengeIdsResponse;

import java.util.List;

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
    private List<BaseCodeResponse> baseCodes;

    public static ChallengeResponse from(Challenge challenge, boolean isCompleted, List<FindBaseCodeByChallengeIdsResponse> baseCodes) {
        ChallengeResponse challengeResponse = new ChallengeResponse();
        assert challenge.getId() != null;
        challengeResponse.setId(challenge.getId().getValue());
        challengeResponse.setTitle(challenge.getTitle());
        challengeResponse.setDescription(challenge.getDescription());
        challengeResponse.setCategory(challenge.getCategory());
        challengeResponse.setDifficulty(challenge.getDifficulty());
        challengeResponse.setUserUsername(challenge.getUserUsername());
        challengeResponse.setCompleted(isCompleted);
        challengeResponse.setBaseCodes(baseCodes.stream().map((FindBaseCodeByChallengeIdsResponse baseCode) ->
            BaseCodeResponse.from(baseCode.getChallengeId(), baseCode.getLanguageName(), baseCode.getCode())
        ).toList());
        return challengeResponse;
    }

    public static ChallengeResponse from(Challenge challenge) {
        return ChallengeResponse.from(challenge, false, List.of());
    }

    public static ChallengeResponse from(Challenge challenge, boolean isCompleted) {
        return ChallengeResponse.from(challenge, isCompleted, List.of());
    }
}
