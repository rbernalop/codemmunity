package org.rbernalop.apitournament.tournament.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.CategoryId;
import org.rbernalop.shared.domain.valueobject.CategoryName;

@Entity
@Table(name = "tournament_challenge_categories")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TournamentChallengeCategory {
    @EmbeddedId
    private CategoryId id;

    @Embedded
    private CategoryName name;

    public static TournamentChallengeCategory create(CategoryId id, CategoryName name) {
        TournamentChallengeCategory category = new TournamentChallengeCategory();
        category.id = id;
        category.name = name;
        return category;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }
}
