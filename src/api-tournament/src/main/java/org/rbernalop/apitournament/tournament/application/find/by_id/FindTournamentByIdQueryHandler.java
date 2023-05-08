package org.rbernalop.apitournament.tournament.application.find.by_id;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdQuery;
import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdResponse;
import org.rbernalop.apitournament.tournament.application.find.TournamentFinder;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindTournamentByIdQueryHandler implements QueryHandler<FindTournamentByIdQuery, FindTournamentByIdResponse> {

    private final TournamentFinder tournamentFinder;

    public FindTournamentByIdQueryHandler(QueryBus queryBus, EventBus eventBus, TournamentRepository tournamentRepository) {
        this.tournamentFinder = new TournamentFinder(queryBus, eventBus, tournamentRepository);
    }

    @Override
    public FindTournamentByIdResponse handle(FindTournamentByIdQuery query) {
        log.info("Finding leaderboard for tournament {}", query.getId());

        TournamentId tournamentId = new TournamentId(query.getId());
        return tournamentFinder.findById(tournamentId);
    }
}
