package org.rbernalop.apiscript.script.application.finder;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.apiscript.script.domain.value_object.PaginationRequest;
import org.rbernalop.apiscript.script.domain.value_object.OwnerUsername;
import org.rbernalop.apiscript.shared.application.script.find.FindScriptsByUserIdResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;

import java.util.List;

public class ScriptFinder extends UseCase {
    private final DomainScriptFinder domainScriptFinder;

    public ScriptFinder(QueryBus queryBus, DomainScriptFinder domainScriptFinder) {
        super(queryBus);
        this.domainScriptFinder = domainScriptFinder;
    }

    public FindScriptsByUserIdResponse findScriptsByUserId(OwnerUsername ownerUsername, PaginationRequest paginationRequest) {
        List<Script> scripts = domainScriptFinder.findScriptsByOwnerUsername(ownerUsername, paginationRequest);
        return new FindScriptsByUserIdResponse(scripts);
    }
}
