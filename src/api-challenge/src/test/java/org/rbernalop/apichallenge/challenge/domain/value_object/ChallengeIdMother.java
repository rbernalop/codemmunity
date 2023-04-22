package org.rbernalop.apichallenge.challenge.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.ChallengeId;

public class ChallengeIdMother {

    public static ChallengeId random() {
        return new ChallengeId(Faker.instance().internet().uuid());
    }
}