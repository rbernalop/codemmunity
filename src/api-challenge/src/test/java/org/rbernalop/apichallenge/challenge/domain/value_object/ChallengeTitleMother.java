package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.ChallengeTitle;

public class ChallengeTitleMother {

    public static ChallengeTitle random() {
        return new ChallengeTitle(Faker.instance().lorem().sentence());
    }
}