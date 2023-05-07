package org.rbernalop.apitournament.shared.application.tournament.join;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JoinTournamentCommand implements Command {
    private String id;
    private String user;
}
