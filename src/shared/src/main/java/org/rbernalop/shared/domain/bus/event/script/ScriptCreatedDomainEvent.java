package org.rbernalop.shared.domain.bus.event.script;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.event.DomainEvent;

@Getter
@Setter
@NoArgsConstructor
public class ScriptCreatedDomainEvent extends DomainEvent {
    private String shareKey;

    public ScriptCreatedDomainEvent(String aggregateId, String shareKey) {
        super(aggregateId);
        this.shareKey = shareKey;
    }
}
