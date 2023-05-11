package org.rbernalop.apitournament.tournament.domain.value_object;

import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallengeCategory;
import org.rbernalop.shared.domain.valueobject.CategoryId;
import org.rbernalop.shared.domain.valueobject.CategoryName;

public class TournamentChallengeCategoryMother {
    public static TournamentChallengeCategory random() {
        CategoryId categoryId = CategoryIdMother.random();
        CategoryName categoryName = CategoryNameMother.random();
        return TournamentChallengeCategory.create(categoryId, categoryName);
    }
}
