package org.rbernalop.servicesynchronization.shared.application.script.create;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.bus.event.script.ScriptCreatedDomainEvent;

public class ScriptCreatedDomainEventMother {
    public static ScriptCreatedDomainEvent random() {
        return new ScriptCreatedDomainEvent(Faker.instance().internet().uuid(),
            Faker.instance().internet().uuid()
        );
    }
}