package org.rbernalop.apitournament.shared.application.tournament.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateTournamentCommand implements Command {
    private String id;
    private String name;
    private String description;
    private String creatorUsername;
    private Date beginningDate;
    private int size;
    private int rounds;
}
