package org.rbernalop.shared.domain;

import org.rbernalop.shared.domain.bus.event.DomainEvent;
import java.util.LinkedList;
import java.util.List;

public class AggregateRoot {
    private List<DomainEvent> recordedDomainEvents = new LinkedList<>();

    final public List<DomainEvent> pullDomainEvents() {
        final var recordedDomainEvents = this.recordedDomainEvents;
        this.recordedDomainEvents = new LinkedList<>();
        return recordedDomainEvents;
    }

    final protected void record(DomainEvent event) {
        recordedDomainEvents.add(event);
    }
}
