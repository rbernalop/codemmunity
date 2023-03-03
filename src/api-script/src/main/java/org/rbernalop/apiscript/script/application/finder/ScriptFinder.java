package org.rbernalop.apiscript.script.application.finder;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.apiscript.script.domain.valueobject.UserId;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.springframework.data.domain.Page;

public class ScriptFinder extends UseCase {
    private final DomainScriptFinder domainScriptFinder;

    public ScriptFinder(QueryBus queryBus, DomainScriptFinder domainScriptFinder) {
        super(queryBus);
        this.domainScriptFinder = domainScriptFinder;
    }

    public FindScriptsByUserIdResponse findScriptsByUserId(UserId userId) {
        Page<Script> scripts = domainScriptFinder.findScriptsByUserId(userId);
        return new FindScriptsByUserIdResponse(scripts.getContent());
    }
}
