package org.rbernalop.shared.infrastructure.bus.event.memory;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.DomainEvent;
import org.rbernalop.shared.domain.bus.event.EventBus;
import java.util.List;

@AllArgsConstructor
@Service
public class SpringApplicationEventBus implements EventBus {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(publisher::publishEvent);
    }

    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }
}
