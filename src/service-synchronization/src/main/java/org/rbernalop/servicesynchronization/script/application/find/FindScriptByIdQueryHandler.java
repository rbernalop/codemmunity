package org.rbernalop.servicesynchronization.script.application.find;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdQuery;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdResponse;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ScriptId;

@Service
@Slf4j
public class FindScriptByIdQueryHandler implements QueryHandler<FindScriptByIdQuery, FindScriptByIdResponse> {

    private final ScriptFinder scriptFinder;

    public FindScriptByIdQueryHandler(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository, ScriptManager scriptManager) {
        this.scriptFinder = new ScriptFinder(queryBus, eventBus, scriptRepository, scriptManager);
    }

    @Override
    public FindScriptByIdResponse handle(FindScriptByIdQuery query) {
        log.info("Finding script by id {}", query.getId());
        ScriptId scriptId = new ScriptId(query.getId());
        return scriptFinder.findById(scriptId);
    }
}
