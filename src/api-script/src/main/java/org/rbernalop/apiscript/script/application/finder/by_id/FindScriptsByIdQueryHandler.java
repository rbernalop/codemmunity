package org.rbernalop.apiscript.script.application.finder.by_id;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.finder.ScriptFinder;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.apiscript.script.domain.value_object.ScriptId;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindScriptsByIdQueryHandler implements QueryHandler<FindScriptsByIdQuery, FindScriptsByIdResponse> {
    private final ScriptFinder scriptFinder;

    public FindScriptsByIdQueryHandler(QueryBus queryBus, ScriptRepository scriptRepository) {
        DomainScriptFinder domainScriptFinder = new DomainScriptFinder(scriptRepository);
        this.scriptFinder = new ScriptFinder(queryBus, domainScriptFinder);
    }

    @Override
    public FindScriptsByIdResponse handle(FindScriptsByIdQuery query) {
        log.info("Finding scripts by id: {}", query.getId());
        ScriptId scriptId = new ScriptId(query.getId());
        return scriptFinder.findScriptById(scriptId);
    }
}
