package org.rbernalop.apitournament.tournament.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apitournament.shared.application.tournament.create.CreateTournamentCommand;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentBeginningDate;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentDescription;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentName;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class CreateTournamentCommandHandler implements CommandHandler<CreateTournamentCommand> {

    private final TournamentCreator tournamentCreator;

    public CreateTournamentCommandHandler(QueryBus queryBus, EventBus eventBus, TournamentRepository repository) {
        this.tournamentCreator = new TournamentCreator(queryBus, eventBus, repository);
    }

    @Override
    public void handle(CreateTournamentCommand command) {
        log.info("Creating tournament {} from user {}", command.getName(), command.getCreatorUsername());

        TournamentId id = new TournamentId(command.getId());
        TournamentName name = new TournamentName(command.getName());
        TournamentDescription description = new TournamentDescription(command.getDescription());
        UserUsername creatorUsername = new UserUsername(command.getCreatorUsername());
        TournamentBeginningDate beginningDate = new TournamentBeginningDate(command.getBeginningDate());
        tournamentCreator.create(id, name, description, creatorUsername, beginningDate);
    }
}
