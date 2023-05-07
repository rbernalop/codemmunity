package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;

public class TournamentDescriptionMother {
    public static TournamentDescription random() {
        return new TournamentDescription(Faker.instance().lorem().sentence());
    }
}
