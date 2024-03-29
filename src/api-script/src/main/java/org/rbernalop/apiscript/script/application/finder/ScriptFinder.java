package org.rbernalop.apiscript.script.application.finder;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.apiscript.shared.application.script.find.by_id.FindScriptsByIdResponse;
import org.rbernalop.apiscript.shared.application.script.find.by_userid.FindScriptsByUsernameResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

public class ScriptFinder extends UseCase {
    private final DomainScriptFinder domainScriptFinder;

    public ScriptFinder(QueryBus queryBus, EventBus eventBus, DomainScriptFinder domainScriptFinder) {
        super(queryBus, eventBus);
        this.domainScriptFinder = domainScriptFinder;
    }

    public FindScriptsByUsernameResponse findScriptsByUserId(UserUsername userUsername, PaginationRequest paginationRequest) {
        List<Script> scripts = domainScriptFinder.findScriptsByuserUsername(userUsername, paginationRequest);
        return new FindScriptsByUsernameResponse(scripts);
    }

    public FindScriptsByIdResponse findScriptById(ScriptId scriptId) {
        Script script = domainScriptFinder.findScriptById(scriptId);
        return new FindScriptsByIdResponse(script);
    }
}
