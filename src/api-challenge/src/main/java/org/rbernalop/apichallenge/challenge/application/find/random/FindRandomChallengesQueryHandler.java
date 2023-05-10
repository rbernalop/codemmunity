package org.rbernalop.apichallenge.challenge.application.find.random;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.challenge.application.find.ChallengeFinder;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.challenge.domain.value_object.NumberChallenges;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesQuery;
import org.rbernalop.apichallenge.shared.application.challenge.find.random.FindRandomChallengesResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindRandomChallengesQueryHandler implements QueryHandler<FindRandomChallengesQuery, FindRandomChallengesResponse> {

    private final ChallengeFinder challengeFinder;

    public FindRandomChallengesQueryHandler(QueryBus queryBus, EventBus eventBus, ChallengeRepository challengeRepository) {
        this.challengeFinder = new ChallengeFinder(queryBus, eventBus, challengeRepository);
    }


    @Override
    public FindRandomChallengesResponse handle(FindRandomChallengesQuery query) {
        log.info("Finding {} random challenges", query.getNumChallenges());

        NumberChallenges numberChallenges = new NumberChallenges(query.getNumChallenges());
        return challengeFinder.findRandomChallenges(numberChallenges);
    }
}
