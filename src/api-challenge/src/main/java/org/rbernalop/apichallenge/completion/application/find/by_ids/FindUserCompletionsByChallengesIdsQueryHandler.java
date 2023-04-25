package org.rbernalop.apichallenge.completion.application.find.by_ids;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.completion.application.find.CompletionFinder;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByChallengesIdsQuery;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByChallengesResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

@Service
@Slf4j
public class FindUserCompletionsByChallengesIdsQueryHandler
        implements QueryHandler<FindUserCompletionsByChallengesIdsQuery, FindUserCompletionsByChallengesResponse> {
    private final CompletionFinder completionFinder;

    public FindUserCompletionsByChallengesIdsQueryHandler(QueryBus queryBus, EventBus eventBus, CompletionRepository completionRepository) {
        this.completionFinder = new CompletionFinder(queryBus, eventBus, completionRepository);
    }

    @Override
    public FindUserCompletionsByChallengesResponse handle(FindUserCompletionsByChallengesIdsQuery query) {
        log.info("Finding user {} completions by challenges ids {}", query.getUsername(), query.getIds());

        List<ChallengeId> challengeIds = query.getIds().stream().map(ChallengeId::new).toList();
        UserUsername username = new UserUsername(query.getUsername());
        return completionFinder.findUserCompletionsByChallengesIds(challengeIds, username);
    }
}
