package org.rbernalop.apitournament.tournament.application.create;

import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.*;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class TournamentCreator extends UseCase {
    private final TournamentRepository repository;

    public TournamentCreator(QueryBus queryBus, EventBus eventBus, TournamentRepository repository) {
        super(queryBus, eventBus);
        this.repository = repository;
    }

    public void create(TournamentId id, TournamentName name, TournamentDescription description,
            UserUsername creatorUsername, TournamentBeginningDate beginningDate, TournamentSize size) {
        Tournament tournament = Tournament.create(id, name, description, creatorUsername, beginningDate, size);
        repository.save(tournament);
    }
}
