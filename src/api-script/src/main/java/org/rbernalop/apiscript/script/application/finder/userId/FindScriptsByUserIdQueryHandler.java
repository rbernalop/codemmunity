package org.rbernalop.apiscript.script.application.finder.userId;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.finder.ScriptFinder;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.apiscript.script.domain.value_object.PaginationRequest;
import org.rbernalop.apiscript.script.domain.value_object.OwnerUsername;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdQuery;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindScriptsByUserIdQueryHandler implements QueryHandler<FindScriptsByUserIdQuery, FindScriptsByUserIdResponse> {
    private final ScriptFinder scriptFinder;

    public FindScriptsByUserIdQueryHandler(QueryBus queryBus, ScriptRepository scriptRepository) {
        DomainScriptFinder domainScriptFinder = new DomainScriptFinder(scriptRepository);
        this.scriptFinder = new ScriptFinder(queryBus, domainScriptFinder);
    }

    @Override
    public FindScriptsByUserIdResponse handle(FindScriptsByUserIdQuery query) {
        log.info("Finding scripts by user id: {}", query.getOwnerUsername());
        OwnerUsername ownerUsername = new OwnerUsername(query.getOwnerUsername());
        PaginationRequest paginationRequest = new PaginationRequest(query.getPage(), query.getSize());
        return scriptFinder.findScriptsByUserId(ownerUsername, paginationRequest);
    }
}
