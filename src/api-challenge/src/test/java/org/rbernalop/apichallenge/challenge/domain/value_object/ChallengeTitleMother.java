package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;

public class ChallengeTitleMother {

    public static ChallengeTitle random() {
        return new ChallengeTitle(Faker.instance().lorem().sentence());
    }
}