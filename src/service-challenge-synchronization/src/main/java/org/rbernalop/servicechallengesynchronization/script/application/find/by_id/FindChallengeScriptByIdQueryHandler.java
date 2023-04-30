package org.rbernalop.servicechallengesynchronization.script.application.find.by_id;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicechallengesynchronization.script.application.find.ChallengeScriptFinder;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindChallengeScriptByIdQuery;
import org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id.FindScriptByChallengeIdResponse;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class FindChallengeScriptByIdQueryHandler
        implements QueryHandler<FindChallengeScriptByIdQuery, FindScriptByChallengeIdResponse> {
    private final ChallengeScriptFinder challengeScriptFinder;

    public FindChallengeScriptByIdQueryHandler(QueryBus queryBus, EventBus eventBus,
           ChallengeScriptRepository challengeScriptRepository, ChallengeScriptManager challengeScriptManager) {
        this.challengeScriptFinder =
            new ChallengeScriptFinder(queryBus, eventBus, challengeScriptRepository, challengeScriptManager);
    }

    @Override
    public FindScriptByChallengeIdResponse handle(FindChallengeScriptByIdQuery query) {
        log.info("Finding challenge script by challenge {} and user {}", query.getChallengeId(), query.getUsername());

        ChallengeId challengeId = new ChallengeId(query.getChallengeId());
        UserUsername username = new UserUsername(query.getUsername());
        ChallengeScriptId id = new ChallengeScriptId(challengeId, username);
        return challengeScriptFinder.findById(id);
    }
}
