package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;

public class TournamentRoundsMother {
    public static TournamentRounds random() {
        return new TournamentRounds(Faker.instance().number().numberBetween(1, 10));
    }
}