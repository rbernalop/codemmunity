package org.rbernalop.apitournament.shared.application.tournament.find.by_id;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitournament.shared.application.tournament.find.CompetitorResponse;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FindTournamentByIdResponse implements Response {
    private String id;
    private String name;
    private String description;
    private Date beginningDate;
    private int size;
    private List<CompetitorResponse> competitors;

    public static FindTournamentByIdResponse from(Tournament tournament) {
        FindTournamentByIdResponse response = new FindTournamentByIdResponse();
        assert tournament.getId() != null;
        response.setId(tournament.getId().getValue());
        response.setName(tournament.getName());
        response.setDescription(tournament.getDescription());
        response.setBeginningDate(tournament.getBeginningDate());
        response.setSize(tournament.getSize());
        response.setCompetitors(tournament.getCompetitors().stream().map(CompetitorResponse::from).toList());
        return response;
    }
}
