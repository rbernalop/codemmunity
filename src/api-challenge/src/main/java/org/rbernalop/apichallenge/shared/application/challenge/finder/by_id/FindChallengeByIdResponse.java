package org.rbernalop.apichallenge.shared.application.challenge.finder.by_id;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.shared.domain.bus.query.Response;

@Getter
@Setter
public class FindChallengeByIdResponse implements Response {
    private String id;
    private String title;
    private String description;
    private String category;
    private long difficulty;
    private String userUsername;

    public static FindChallengeByIdResponse from(Challenge challenge) {
        FindChallengeByIdResponse response = new FindChallengeByIdResponse();
        assert challenge.getId() != null;
        response.setId(challenge.getId().getValue());
        response.setTitle(challenge.getTitle());
        response.setDescription(challenge.getDescription());
        response.setCategory(challenge.getCategory());
        response.setDifficulty(challenge.getDifficulty());
        response.setUserUsername(challenge.getUserUsername());
        return response;
    }
}
