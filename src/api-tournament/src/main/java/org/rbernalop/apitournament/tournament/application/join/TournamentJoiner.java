package org.rbernalop.apitournament.tournament.application.join;

import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.service.DomainTournamentFinder;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class TournamentJoiner extends UseCase {
    private final TournamentRepository tournamentRepository;
    private final DomainTournamentFinder domainTournamentFinder;

    public TournamentJoiner(QueryBus queryBus, EventBus eventBus, TournamentRepository tournamentRepository) {
        super(queryBus, eventBus);
        this.tournamentRepository = tournamentRepository;
        this.domainTournamentFinder = new DomainTournamentFinder(tournamentRepository);
    }

    public void join(TournamentId tournamentId, UserUsername user) {
        Tournament tournament = domainTournamentFinder.find(tournamentId);
        tournament.join(user);
        tournamentRepository.save(tournament);
    }
}
