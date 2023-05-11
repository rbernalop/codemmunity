package org.rbernalop.apitournament.tournament.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apitournament.shared.application.tournament.create.CreateTournamentCommand;
import org.rbernalop.apitournament.tournament.domain.port.RandomChallengeRetriever;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.*;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class CreateTournamentCommandHandler implements CommandHandler<CreateTournamentCommand> {

    private final TournamentCreator tournamentCreator;

    public CreateTournamentCommandHandler(QueryBus queryBus, EventBus eventBus, TournamentRepository repository,
          RandomChallengeRetriever randomChallengeRetriever) {
        this.tournamentCreator = new TournamentCreator(queryBus, eventBus, repository, randomChallengeRetriever);
    }

    @Override
    public void handle(CreateTournamentCommand command) {
        log.info("Creating tournament {} from user {}", command.getName(), command.getCreatorUsername());

        TournamentId id = new TournamentId(command.getId());
        TournamentName name = new TournamentName(command.getName());
        TournamentDescription description = new TournamentDescription(command.getDescription());
        UserUsername creatorUsername = new UserUsername(command.getCreatorUsername());
        TournamentBeginningDate beginningDate = new TournamentBeginningDate(command.getBeginningDate());
        TournamentSize size = new TournamentSize(command.getSize());
        TournamentRounds rounds = new TournamentRounds(command.getRounds());
        tournamentCreator.create(id, name, description, creatorUsername, beginningDate, size, rounds);
    }
}
