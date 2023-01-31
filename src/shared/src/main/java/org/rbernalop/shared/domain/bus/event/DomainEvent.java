package org.rbernalop.shared.domain.bus.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class DomainEvent {
    private String aggregateId;
    private String eventId;
    private String occurredOn;
    private String eventName;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId     = UUID.randomUUID().toString();
        this.occurredOn  = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public abstract HashMap<String, Serializable> toPrimitives();

    public abstract DomainEvent fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    );
}