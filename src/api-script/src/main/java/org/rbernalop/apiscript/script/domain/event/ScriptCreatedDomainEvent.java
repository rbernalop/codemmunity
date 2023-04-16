package org.rbernalop.apiscript.script.domain.event;

import lombok.Getter;
import org.rbernalop.shared.domain.bus.event.DomainEvent;

@Getter
public class ScriptCreatedDomainEvent extends DomainEvent {
    private final String shareKey;

    public ScriptCreatedDomainEvent(String aggregateId, String shareKey) {
        super(aggregateId);
        this.shareKey = shareKey;
    }
}
