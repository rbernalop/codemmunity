package org.rbernalop.apitournament.tournament.application.create;

import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.apitournament.tournament.domain.port.RandomChallengeRetriever;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.*;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

public class TournamentCreator extends UseCase {
    private final TournamentRepository repository;
    private final RandomChallengeRetriever randomChallengeRetriever;

    public TournamentCreator(QueryBus queryBus, EventBus eventBus, TournamentRepository repository,
         RandomChallengeRetriever randomChallengeRetriever) {
        super(queryBus, eventBus);
        this.repository = repository;
        this.randomChallengeRetriever = randomChallengeRetriever;
    }

    public void create(TournamentId id, TournamentName name, TournamentDescription description,
            UserUsername creatorUsername, TournamentBeginningDate beginningDate, TournamentSize size,
           TournamentRounds rounds) {
        List<TournamentChallenge> challenges = randomChallengeRetriever.getRandomChallenges(rounds.getValue());
        Tournament tournament =
            Tournament.create(id, name, description, creatorUsername, beginningDate, size, challenges, rounds);
        repository.save(tournament);
    }
}
