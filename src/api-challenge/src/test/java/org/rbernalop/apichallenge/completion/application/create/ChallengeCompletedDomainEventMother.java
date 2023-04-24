package org.rbernalop.apichallenge.completion.application.create;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.bus.event.test.ChallengeCompletedDomainEvent;

public class ChallengeCompletedDomainEventMother {
    public static ChallengeCompletedDomainEvent random() {
        return new ChallengeCompletedDomainEvent(
                Faker.instance().internet().uuid(),
                Faker.instance().name().username(),
                Faker.instance().lorem().sentence(),
                Faker.instance().lorem().word()
        );
    }
}