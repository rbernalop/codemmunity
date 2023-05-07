package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;

public class TournamentIdMother {
    public static TournamentId random() {
        return new TournamentId(Faker.instance().internet().uuid());
    }
}