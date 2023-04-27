package org.rbernalop.apichallenge.challenge.application.find.all;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.challenge.application.find.ChallengeFinder;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.challenge.find.all.FindChallengesPaginatedQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.all.FindChallengesPaginatedResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class FindChallengesPaginatedQueryHandler implements QueryHandler<FindChallengesPaginatedQuery, FindChallengesPaginatedResponse> {
    private final ChallengeFinder challengeFinder;

    public FindChallengesPaginatedQueryHandler(QueryBus queryBus, EventBus eventBus, ChallengeRepository challengeRepository) {
        this.challengeFinder = new ChallengeFinder(queryBus, eventBus, challengeRepository);
    }

    @Override
    public FindChallengesPaginatedResponse handle(FindChallengesPaginatedQuery query) {
        log.info("Finding challenges paginated with page {} and size {}", query.getPage(), query.getSize());

        PaginationRequest paginationRequest = new PaginationRequest(query.getPage(), query.getSize());
        UserUsername requesterUsername = new UserUsername(query.getRequesterUsername());
        return challengeFinder.findChallengesPaginated(paginationRequest, requesterUsername);
    }
}
