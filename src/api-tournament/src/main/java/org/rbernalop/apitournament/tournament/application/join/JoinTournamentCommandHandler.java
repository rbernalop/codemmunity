package org.rbernalop.apitournament.tournament.application.join;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apitournament.shared.application.tournament.join.JoinTournamentCommand;
import org.rbernalop.apitournament.tournament.domain.port.TournamentRepository;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentId;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class JoinTournamentCommandHandler implements CommandHandler<JoinTournamentCommand> {

    private final TournamentJoiner tournamentJoiner;

    public JoinTournamentCommandHandler(QueryBus queryBus, EventBus eventBus, TournamentRepository tournamentRepository) {
        this.tournamentJoiner = new TournamentJoiner(queryBus, eventBus, tournamentRepository);
    }

    @Override
    public void handle(JoinTournamentCommand command) {
        log.info("User {} joining tournament {}", command.getUser(), command.getId());

        TournamentId tournamentId = new TournamentId(command.getId());
        UserUsername user = new UserUsername(command.getUser());
        tournamentJoiner.join(tournamentId, user);
    }
}
