package org.rbernalop.apichallenge.challenge.application.find.by_id;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.challenge.application.find.ChallengeFinder;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindChallengeByIdQueryHandler implements QueryHandler<FindChallengeByIdQuery, FindChallengeByIdResponse> {

    private final ChallengeFinder challengeFinder;

    public FindChallengeByIdQueryHandler(QueryBus queryBus, EventBus eventBus, ChallengeRepository challengeRepository) {
        this.challengeFinder = new ChallengeFinder(queryBus, eventBus, challengeRepository);
    }

    @Override
    public FindChallengeByIdResponse handle(FindChallengeByIdQuery query) {
        log.info("Finding challenge by id: {}", query.getId());

        ChallengeId challengeId = new ChallengeId(query.getId());
        return challengeFinder.findById(challengeId);
    }
}
