package org.rbernalop.apichallenge.baseCode.application.find.by_challenge_ids;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.baseCode.application.find.BaseCodeFinder;
import org.rbernalop.apichallenge.baseCode.domain.port.BaseCodeRepository;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids.FindBaseCodeByChallengeIdsQuery;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids.FindBaseCodeByChallengeIdsResponses;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ChallengeId;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FindBaseCodeByChallengeIdsQueryHandler implements QueryHandler<FindBaseCodeByChallengeIdsQuery, FindBaseCodeByChallengeIdsResponses> {
    private final BaseCodeFinder baseCodeFinder;

    public FindBaseCodeByChallengeIdsQueryHandler(QueryBus queryBus, EventBus eventBus, BaseCodeRepository baseCodeRepository) {
        this.baseCodeFinder = new BaseCodeFinder(queryBus, eventBus, baseCodeRepository);
    }

    @Override
    public FindBaseCodeByChallengeIdsResponses handle(FindBaseCodeByChallengeIdsQuery query) {
        log.info("Finding base codes by challenge ids: {}", query.getChallengeIds());

        List<ChallengeId> challengeIds = new ArrayList<>();
        for (String challengeId: query.getChallengeIds()) {
            challengeIds.add(new ChallengeId(challengeId));
        }
        return baseCodeFinder.findByChallengeIds(challengeIds);
    }
}
