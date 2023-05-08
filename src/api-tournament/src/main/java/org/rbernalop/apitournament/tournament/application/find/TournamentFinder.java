package org.rbernalop.apitournament.tournament.application.find;

import org.rbernalop.apitournament.shared.application.tournament.find.by_id.FindTournamentByIdResponse;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.service.DomainTournamentFinder;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;

public class TournamentFinder extends UseCase {
    private final DomainTournamentFinder domainTournamentFinder;

    public TournamentFinder(QueryBus queryBus, EventBus eventBus, TournamentRepository tournamentRepository) {
        super(queryBus, eventBus);
        this.domainTournamentFinder = new DomainTournamentFinder(tournamentRepository);
    }


    public FindTournamentByIdResponse findById(TournamentId tournamentId) {
        Tournament tournament = domainTournamentFinder.find(tournamentId);
        return FindTournamentByIdResponse.from(tournament);
    }
}
