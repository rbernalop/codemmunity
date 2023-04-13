package org.rbernalop.apichallenge.challenge.domain.aggregate;

import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.apichallenge.challenge.domain.entity.ChallengeCategoryMother;
import org.rbernalop.apichallenge.challenge.domain.value_object.*;

public class ChallengeMother {

    public static Challenge random() {
        return Challenge.create(
            ChallengeIdMother.random(),
            ChallengeTitleMother.random(),
            ChallengeDescriptionMother.random(),
            UserUsernameMother.random(),
            ChallengeDifficultyMother.random(),
            ChallengeCategoryMother.random()
        );
    }

    public static Challenge randomFromCategory(Category category) {
        return Challenge.create(
            ChallengeIdMother.random(),
            ChallengeTitleMother.random(),
            ChallengeDescriptionMother.random(),
            UserUsernameMother.random(),
            ChallengeDifficultyMother.random(),
            category
        );
    }
}