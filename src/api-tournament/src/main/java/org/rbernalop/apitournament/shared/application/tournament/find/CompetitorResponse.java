package org.rbernalop.apitournament.shared.application.tournament.find;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitournament.tournament.domain.entity.Competitor;

@Getter
@Setter
public class CompetitorResponse {
    private String username;
    private Integer points;

    public static CompetitorResponse from(Competitor competitor) {
        CompetitorResponse response = new CompetitorResponse();
        response.setUsername(competitor.getCompetitorUsername());
        response.setPoints(competitor.getPoints());
        return response;
    }
}
