package org.rbernalop.apichallenge.challenge.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.shared.application.challenge.finder.ChallengeResponse;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedQuery;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ChallengeGetController extends ApiController {
    public ChallengeGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("challenge")
    public ChallengeGetResponses getChallenge(@RequestParam("page") int page, @RequestParam("size") int size) {
        FindChallengesPaginatedQuery findChallengesPaginatedQuery = new FindChallengesPaginatedQuery();
        findChallengesPaginatedQuery.setPage(page);
        findChallengesPaginatedQuery.setSize(size);

        FindChallengesPaginatedResponse findChallengesPaginatedResponse = ask(findChallengesPaginatedQuery);

        return ChallengeGetResponses.from(findChallengesPaginatedResponse);
    }
}

@Getter
@Setter
class ChallengeGetResponses {
    private List<ChallengeGetResponse> challenges;
    private long totalChallenges;

    public static ChallengeGetResponses from(FindChallengesPaginatedResponse findChallengesPaginatedResponse) {
        ChallengeGetResponses challengeGetResponses = new ChallengeGetResponses();
        challengeGetResponses.setChallenges(
            findChallengesPaginatedResponse.getChallenges().stream().map(ChallengeGetResponse::from).toList()
        );
        challengeGetResponses.setTotalChallenges(findChallengesPaginatedResponse.getTotalChallenges());
        return challengeGetResponses;
    }
}

@Getter
@Setter
class ChallengeGetResponse {
    private String id;
    private String title;
    private String category;
    private long difficulty;

    public static ChallengeGetResponse from(ChallengeResponse challengeResponse) {
        ChallengeGetResponse challengeGetResponse = new ChallengeGetResponse();
        challengeGetResponse.setId(challengeResponse.getId());
        challengeGetResponse.setTitle(challengeResponse.getTitle());
        challengeGetResponse.setCategory(challengeResponse.getCategory());
        challengeGetResponse.setDifficulty(challengeResponse.getDifficulty());
        return challengeGetResponse;
    }
}
