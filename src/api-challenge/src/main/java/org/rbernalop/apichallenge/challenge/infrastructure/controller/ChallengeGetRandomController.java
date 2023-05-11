package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import org.rbernalop.apichallenge.shared.application.challenge.find.ChallengeResponse;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomResponse;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ChallengeGetRandomController extends ApiController {
    public ChallengeGetRandomController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("challenge/random")
    public ChallengeGetRandomResponses handle(@RequestParam("numChallenges") int numChallenges) {
        FindRandomChallengesQuery query = new FindRandomChallengesQuery(numChallenges);
        FindRandomChallengesResponse response = ask(query);

        List<ChallengeGetRandomResponse> challenges = response.getChallenges().stream().map((ChallengeResponse challenge) ->
            new ChallengeGetRandomResponse(
                challenge.getId(), challenge.getTitle(), challenge.getDescription(),
                challenge.getCategory(), challenge.getUserUsername(), challenge.getDifficulty()
            )).toList();

        return ChallengeGetRandomResponses.from(challenges);
    }
}
