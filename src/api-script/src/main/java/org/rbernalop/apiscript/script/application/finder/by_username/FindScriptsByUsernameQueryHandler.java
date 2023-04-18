package org.rbernalop.apiscript.script.application.finder.by_username;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiscript.script.application.finder.ScriptFinder;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.rbernalop.apiscript.shared.application.script.find.by_userid.FindScriptsByUsernameQuery;
import org.rbernalop.apiscript.shared.application.script.find.by_userid.FindScriptsByUsernameResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class FindScriptsByUsernameQueryHandler implements QueryHandler<FindScriptsByUsernameQuery, FindScriptsByUsernameResponse> {
    private final ScriptFinder scriptFinder;

    public FindScriptsByUsernameQueryHandler(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository) {
        DomainScriptFinder domainScriptFinder = new DomainScriptFinder(scriptRepository);
        this.scriptFinder = new ScriptFinder(queryBus, eventBus, domainScriptFinder);
    }

    @Override
    public FindScriptsByUsernameResponse handle(FindScriptsByUsernameQuery query) {
        log.info("Finding scripts by username: {}", query.getUserUsername());
        UserUsername userUsername = new UserUsername(query.getUserUsername());
        PaginationRequest paginationRequest = new PaginationRequest(query.getPage(), query.getSize());
        return scriptFinder.findScriptsByUserId(userUsername, paginationRequest);
    }
}
