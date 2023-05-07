package org.rbernalop.apitournament.tournament.infrastructure.controller;

import org.rbernalop.apitournament.shared.application.tournament.join.JoinTournamentCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class TournamentPostJoinController extends ApiController {

    public TournamentPostJoinController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("tournament/{id}/join")
    public void handle(@RequestParam("user") String user, @PathVariable String id) {
        JoinTournamentCommand command = new JoinTournamentCommand(id, user);
        dispatch(command);
    }
}
