package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.ChallengeDescription;

public class ChallengeDescriptionMother {
    public static ChallengeDescription random() {
        return new ChallengeDescription(Faker.instance().lorem().sentence());
    }
}
