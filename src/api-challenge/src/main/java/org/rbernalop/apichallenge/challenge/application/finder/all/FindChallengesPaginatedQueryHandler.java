package org.rbernalop.apichallenge.challenge.application.finder.all;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.challenge.application.finder.ChallengeFinder;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedQuery;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;

@Service
@Slf4j
public class FindChallengesPaginatedQueryHandler implements QueryHandler<FindChallengesPaginatedQuery, FindChallengesPaginatedResponse> {
    private final ChallengeFinder challengeFinder;

    public FindChallengesPaginatedQueryHandler(ChallengeRepository challengeRepository, QueryBus queryBus) {
        this.challengeFinder = new ChallengeFinder(challengeRepository, queryBus);
    }

    @Override
    public FindChallengesPaginatedResponse handle(FindChallengesPaginatedQuery query) {
        log.info("Finding challenges paginated with page {} and size {}", query.getPage(), query.getSize());
        PaginationRequest paginationRequest = new PaginationRequest(query.getPage(), query.getSize());
        return challengeFinder.findChallengesPaginated(paginationRequest);
    }
}
