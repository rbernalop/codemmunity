package org.rbernalop.shared.domain.bus.event.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.event.DomainEvent;


@Getter
@Setter
@NoArgsConstructor
public class ChallengeCompletedDomainEvent extends DomainEvent {
    private String username;
    private String scriptContent;
    private String languageName;

    public ChallengeCompletedDomainEvent(String aggregateId, String username, String scriptContent, String languageName) {
        super(aggregateId);
        this.username = username;
        this.scriptContent = scriptContent;
        this.languageName = languageName;
    }
}
