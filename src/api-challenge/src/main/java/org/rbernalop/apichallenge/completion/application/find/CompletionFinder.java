package org.rbernalop.apichallenge.completion.application.find;

import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByIdsResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

public class CompletionFinder extends UseCase {
    private final CompletionRepository completionRepository;

    public CompletionFinder(QueryBus queryBus, EventBus eventBus, CompletionRepository completionRepository) {
        super(queryBus, eventBus);
        this.completionRepository = completionRepository;
    }

    public FindUserCompletionsByIdsResponse findUserCompletionsByChallengesIds(List<ChallengeId> challengeIds, UserUsername username) {
        List<Completion> completions = completionRepository.findByChallengeIdInAndUserUsername(challengeIds, username);
        return FindUserCompletionsByIdsResponse.from(completions);
    }
}
