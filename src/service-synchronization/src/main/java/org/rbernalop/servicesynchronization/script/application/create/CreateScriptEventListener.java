package org.rbernalop.servicesynchronization.script.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.EventListener;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.event.script.ScriptCreatedDomainEvent;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.ShareKey;

@Service
@Slf4j
public class CreateScriptEventListener {
    private final ScriptCreator scriptCreator;

    public CreateScriptEventListener(QueryBus queryBus, EventBus eventBus, ScriptRepository scriptRepository,
            ScriptManager scriptManager) {
        this.scriptCreator = new ScriptCreator(queryBus, eventBus, scriptRepository, scriptManager);
    }

    @EventListener
    public void handle(ScriptCreatedDomainEvent event) {
        log.info("Script created event received for script {}", event.getAggregateId());

        ScriptId scriptId = new ScriptId(event.getAggregateId());
        ShareKey shareKey = new ShareKey(event.getShareKey());
        ScriptContent content = new ScriptContent("");
        scriptCreator.create(scriptId, shareKey, content);
    }
}
