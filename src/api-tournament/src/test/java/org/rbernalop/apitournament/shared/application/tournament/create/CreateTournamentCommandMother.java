package org.rbernalop.apitournament.shared.application.tournament.create;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

public class CreateTournamentCommandMother {
    public static CreateTournamentCommand random() {
        return new CreateTournamentCommand(
                Faker.instance().internet().uuid(),
                Faker.instance().name().name(),
                Faker.instance().lorem().paragraph(),
                Faker.instance().name().username(),
                Date.from(Instant.now().plus(Period.ofDays(1))),
                Faker.instance().number().numberBetween(2, 15)
        );
    }

    public static CreateTournamentCommand randomWithInvalidName() {
        return new CreateTournamentCommand(
                Faker.instance().internet().uuid(),
                "",
                Faker.instance().lorem().paragraph(),
                Faker.instance().name().username(),
                Date.from(Instant.now().plus(Period.ofDays(1))),
                Faker.instance().number().numberBetween(2, 15)
        );
    }

    public static CreateTournamentCommand randomWithInvalidDescription() {
        return new CreateTournamentCommand(
                Faker.instance().internet().uuid(),
                Faker.instance().name().name(),
                "",
                Faker.instance().name().username(),
                Date.from(Instant.now().plus(Period.ofDays(1))),
                Faker.instance().number().numberBetween(2, 15)
        );
    }

    public static CreateTournamentCommand randomWithInvalidDate() {
        return new CreateTournamentCommand(
                Faker.instance().internet().uuid(),
                Faker.instance().name().name(),
                Faker.instance().lorem().paragraph(),
                Faker.instance().name().username(),
                Date.from(Instant.now().minus(Period.ofDays(1))),
                Faker.instance().number().numberBetween(2, 15)
        );
    }

    public static CreateTournamentCommand randomWithInvalidSize() {
        return new CreateTournamentCommand(
                Faker.instance().internet().uuid(),
                Faker.instance().name().name(),
                Faker.instance().lorem().paragraph(),
                Faker.instance().name().username(),
                Date.from(Instant.now().plus(Period.ofDays(1))),
                Faker.instance().number().numberBetween(16, 100)
        );
    }
}