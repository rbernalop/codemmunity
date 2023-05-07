package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;

public class TournamentNameMother {
    public static TournamentName random() {
        return new TournamentName(Faker.instance().name().name());
    }
}