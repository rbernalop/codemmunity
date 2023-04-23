package org.rbernalop.shared.domain.bus.event.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.event.DomainEvent;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeCompletedDomainEvent extends DomainEvent {
    private String username;
    private String challengeId;
    private String scriptContent;
    private String languageName;

    public ChallengeCompletedDomainEvent(String aggregateId) {
        super(aggregateId);
    }
}
