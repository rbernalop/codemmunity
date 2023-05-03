package org.rbernalop.apichallenge.challenge.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apichallenge.challenge.domain.entity.Category;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeDescription;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeDifficulty;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeTitle;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Entity
@Table(name = "challenges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Challenge extends AggregateRoot {
    @EmbeddedId
    private ChallengeId id;

    @Embedded
    private ChallengeTitle title;

    @Embedded
    private ChallengeDescription description;

    @Embedded
    private UserUsername userUsername;

    @Embedded
    private ChallengeDifficulty difficulty;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public static Challenge create(ChallengeId id, ChallengeTitle title, ChallengeDescription description,
            UserUsername userUsername, ChallengeDifficulty difficulty, Category category) {
        Challenge challenge = new Challenge();
        challenge.id = id;
        challenge.title = title;
        challenge.description = description;
        challenge.userUsername = userUsername;
        challenge.difficulty = difficulty;
        challenge.category = category;
        return challenge;
    }

    @Override
    public ChallengeId getId() {
        return id;
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public String getUserUsername() {
        return userUsername.getValue();
    }

    public Long getDifficulty() {
        return difficulty.getValue();
    }

    public String getCategory() {
        return category.getName();
    }
}
