package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;

public class ChallengeDescriptionMother {

    public static ChallengeDescription random() {
        return new ChallengeDescription(Faker.instance().lorem().sentence());
    }
}