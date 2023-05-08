package org.rbernalop.apitournament.tournament.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitournament.shared.application.tournament.find.CompetitorResponse;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdQuery;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TournamentGetByIdController extends ApiController {

    public TournamentGetByIdController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("tournament/{id}")
    public TournamentGetByIdResponse handle(@PathVariable String id) {
        FindTournamentByIdQuery query = new FindTournamentByIdQuery(id);
        FindTournamentByIdResponse response = ask(query);
        return TournamentGetByIdResponse.from(response);
    }
}

@Getter
@Setter
class TournamentGetByIdResponse {
    private String id;
    private String name;
    private String description;
    private Date beginningDate;
    private int size;
    private List<LeaderboardGetByTournamentIdResponse> leaderboard;

    public static TournamentGetByIdResponse from(FindTournamentByIdResponse competitor) {
        TournamentGetByIdResponse response = new TournamentGetByIdResponse();
        assert competitor.getId() != null;
        response.setId(competitor.getId());
        response.setName(competitor.getName());
        response.setDescription(competitor.getDescription());
        response.setBeginningDate(competitor.getBeginningDate());
        response.setSize(competitor.getSize());
        response.setLeaderboard(competitor.getCompetitors().stream().map(LeaderboardGetByTournamentIdResponse::from).toList());
        return response;
    }
}

@Getter
@Setter
class LeaderboardGetByTournamentIdResponse {
    private String username;
    private int points;

    public static LeaderboardGetByTournamentIdResponse from(CompetitorResponse competitorResponse) {
        LeaderboardGetByTournamentIdResponse response = new LeaderboardGetByTournamentIdResponse();
        response.setUsername(competitorResponse.getUsername());
        response.setPoints(competitorResponse.getPoints());
        return response;
    }
}