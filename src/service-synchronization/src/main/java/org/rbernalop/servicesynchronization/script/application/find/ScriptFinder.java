package org.rbernalop.servicesynchronization.script.application.find;

import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.servicesynchronization.shared.application.script.find.FindScriptByIdResponse;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdQuery;
import org.rbernalop.servicesynchronization.shared.application.user_script.find.FindUsersByScriptIdResponse;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.ScriptId;

import java.util.Optional;

public class ScriptFinder extends UseCase {

    private final ScriptRepository scriptRepository;
    private final ScriptManager scriptManager;

    public ScriptFinder(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository, ScriptManager scriptManager) {
        super(queryBus, eventBus);
        this.scriptRepository = scriptRepository;
        this.scriptManager = scriptManager;
    }

    public FindScriptByIdResponse findById(ScriptId id) {
        ScriptContent content = scriptManager.getScriptContent(id);

        if(content == null) {
            Optional<Script> script = scriptRepository.findById(id);
            if(script.isEmpty()) {
                throw new EntityNotFoundException("Script with id " + id.getValue() + " not found");
            } else {
                content = new ScriptContent(script.get().getContent());
            }
            scriptManager.setScriptContent(id, content);
        }


        FindUsersByScriptIdQuery findUsersByScriptIdQuery = new FindUsersByScriptIdQuery(id.getValue());
        FindUsersByScriptIdResponse findUsersByScriptIdResponse = ask(findUsersByScriptIdQuery);

        return new FindScriptByIdResponse(content.getValue(), findUsersByScriptIdResponse.getUsernames());
    }
}
