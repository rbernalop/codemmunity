package org.rbernalop.apitournament.shared.application.tournament.find;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;

@Getter
@Setter
public class ChallengeResponse {
    private String id;
    private String title;
    private String description;
    private String category;
    private long difficulty;
    private String creatorUsername;
    private String baseScript;

    public static ChallengeResponse from(TournamentChallenge challenge) {
        ChallengeResponse response = new ChallengeResponse();
        response.setId(challenge.getId().getValue());
        response.setTitle(challenge.getTitle());
        response.setDescription(challenge.getDescription());
        response.setCategory(challenge.getCategory());
        response.setDifficulty(challenge.getDifficulty());
        response.setCreatorUsername(challenge.getUserUsername());
        response.setBaseScript(""); // TODO: Get base script from challenge
        return response;
    }
}
