package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.ChallengeDifficulty;

public class ChallengeDifficultyMother {
    public static ChallengeDifficulty random() {
        return new ChallengeDifficulty(Faker.instance().number().numberBetween(1, 5));
    }
}
