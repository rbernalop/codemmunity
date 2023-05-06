package org.rbernalop.apitournament.tournament.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitournament.shared.application.tournament.create.CreateTournamentCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1")
public class TournamentPostController extends ApiController {
    public TournamentPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("tournament")
    void handle(@RequestBody TournamentPostRequest request, @RequestParam("user") String creatorUserName) {
        CreateTournamentCommand command = new CreateTournamentCommand(
                request.getId(),
                request.getName(),
                request.getDescription(),
                creatorUserName,
                request.getBeginningDate()
        );

        dispatch(command);
    }
}

@Getter
@Setter
class TournamentPostRequest {
    private String id;
    private String name;
    private String description;
    private Date beginningDate;
}