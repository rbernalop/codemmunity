package org.rbernalop.shared.domain.bus.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DomainEventSubscriber {
    Class<? extends org.rbernalop.shared.domain.bus.event.DomainEvent>[] value();
}
