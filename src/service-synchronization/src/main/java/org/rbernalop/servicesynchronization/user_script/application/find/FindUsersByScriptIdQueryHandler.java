package org.rbernalop.servicesynchronization.user_script.application.find;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdQuery;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdResponse;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ScriptId;

@Service
@Slf4j
public class FindUsersByScriptIdQueryHandler implements QueryHandler<FindUsersByScriptIdQuery, FindUsersByScriptIdResponse> {
    private final UserScriptFinder userScriptFinder;

    public FindUsersByScriptIdQueryHandler(QueryBus queryBus, EventBus eventBus, ScriptManager scriptManager) {
        this.userScriptFinder = new UserScriptFinder(queryBus, eventBus, scriptManager);
    }

    @Override
    public FindUsersByScriptIdResponse handle(FindUsersByScriptIdQuery query) {
        log.info("Finding users by script id {}", query.getScriptId());
        ScriptId scriptId = new ScriptId(query.getScriptId());
        return userScriptFinder.findUsersByScriptId(scriptId);
    }
}
