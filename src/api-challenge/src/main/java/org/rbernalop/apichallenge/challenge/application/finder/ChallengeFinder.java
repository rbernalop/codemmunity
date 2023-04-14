package org.rbernalop.apichallenge.challenge.application.finder;

import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedResponse;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class ChallengeFinder {
    private final ChallengeRepository challengeRepository;
    private final QueryBus queryBus;

    public ChallengeFinder(ChallengeRepository challengeRepository, QueryBus queryBus) {
        this.challengeRepository = challengeRepository;
        this.queryBus = queryBus;
    }

    public FindChallengesPaginatedResponse findChallengesPaginated(PaginationRequest paginationRequest) {

        List<Challenge> challenges = challengeRepository.findAll(
            PageRequest.of(paginationRequest.page(), paginationRequest.size())
        ).getContent();
        return FindChallengesPaginatedResponse.from(challenges, challengeRepository.count());
    }
}
