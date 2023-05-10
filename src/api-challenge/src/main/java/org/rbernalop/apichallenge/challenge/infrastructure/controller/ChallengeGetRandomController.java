package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.shared.application.challenge.find.ChallengeResponse;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
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
        return ChallengeGetRandomResponses.from(response);
    }
}

@Getter
@Setter
class ChallengeGetRandomResponses {
    private List<ChallengeGetRandomResponse> challenges;

    public static ChallengeGetRandomResponses from(FindRandomChallengesResponse findRandomChallengesResponse) {
        ChallengeGetRandomResponses challengeGetResponses = new ChallengeGetRandomResponses();
        challengeGetResponses.setChallenges(
            findRandomChallengesResponse.getChallenges().stream().map(ChallengeGetRandomResponse::from).toList()
        );
        return challengeGetResponses;
    }
}

@Getter
@Setter
class ChallengeGetRandomResponse {
    private String id;
    private String title;
    private String description;
    private String category;
    private String userUsername;
    private long difficulty;

    public static ChallengeGetRandomResponse from(ChallengeResponse challengeResponse) {
        ChallengeGetRandomResponse challengeGetRandomResponse = new ChallengeGetRandomResponse();
        challengeGetRandomResponse.setId(challengeResponse.getId());
        challengeGetRandomResponse.setTitle(challengeResponse.getTitle());
        challengeGetRandomResponse.setDescription(challengeResponse.getDescription());
        challengeGetRandomResponse.setCategory(challengeResponse.getCategory());
        challengeGetRandomResponse.setUserUsername(challengeResponse.getUserUsername());
        challengeGetRandomResponse.setDifficulty(challengeResponse.getDifficulty());
        return challengeGetRandomResponse;
    }
}
