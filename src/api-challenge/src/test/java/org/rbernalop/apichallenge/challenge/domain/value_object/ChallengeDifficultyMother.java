package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;

public class ChallengeDifficultyMother {

    public static ChallengeDifficulty random() {
        return new ChallengeDifficulty(Faker.instance().number().numberBetween(1, 5));
    }
}