package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;

public class TournamentSizeMother {
    public static TournamentSize random() {
        return new TournamentSize(Faker.instance().number().numberBetween(2, 15));
    }
}
