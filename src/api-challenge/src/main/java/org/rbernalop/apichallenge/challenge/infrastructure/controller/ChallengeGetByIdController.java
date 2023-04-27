package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ChallengeGetByIdController extends ApiController {
    public ChallengeGetByIdController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("challenge/{id}")
    public ChallengeGetByIdResponse getChallengeById(@PathVariable String id) {
        FindChallengeByIdQuery query = new FindChallengeByIdQuery(id);
        FindChallengeByIdResponse response = ask(query);
        return new ChallengeGetByIdResponse(response);
    }
}

@Getter
@Setter
@NoArgsConstructor
class ChallengeGetByIdResponse {
    private String id;
    private String title;
    private String description;
    private String category;
    private long difficulty;
    private String creatorUsername;

    public ChallengeGetByIdResponse(FindChallengeByIdResponse response) {
        this.id = response.getId();
        this.title = response.getTitle();
        this.description = response.getDescription();
        this.category = response.getCategory();
        this.difficulty = response.getDifficulty();
        this.creatorUsername = response.getUserUsername();
    }
}
