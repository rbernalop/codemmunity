package org.rbernalop.apitournament.tournament.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.shared.domain.valueobject.*;

import java.util.List;

@Entity
@Table(name = "tournament_challenges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class TournamentChallenge {
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private TournamentChallengeCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tournament tournament;

    @OneToMany(mappedBy = "id.challenge", cascade = CascadeType.ALL)
    private List<TournamentChallengeBaseCode> baseCodes;

    public static TournamentChallenge create(ChallengeId id, ChallengeTitle title, ChallengeDescription description,
            UserUsername userUsername, ChallengeDifficulty difficulty, TournamentChallengeCategory category) {
        TournamentChallenge challenge = new TournamentChallenge();
        challenge.id = id;
        challenge.title = title;
        challenge.description = description;
        challenge.userUsername = userUsername;
        challenge.difficulty = difficulty;
        challenge.category = category;
        return challenge;
    }

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

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public void setBaseCodes(List<TournamentChallengeBaseCode> baseCodes) {
        this.baseCodes = baseCodes;
    }
}
