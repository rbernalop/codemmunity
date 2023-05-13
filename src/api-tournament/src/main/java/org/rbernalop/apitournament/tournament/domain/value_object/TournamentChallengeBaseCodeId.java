package org.rbernalop.apitournament.tournament.domain.value_object;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.shared.domain.valueobject.LanguageName;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class TournamentChallengeBaseCodeId implements Serializable {
    @ManyToOne
    private TournamentChallenge challenge;

    private LanguageName languageName;

    public TournamentChallengeBaseCodeId(TournamentChallenge challenge, LanguageName languageName) {
        this.challenge = challenge;
        this.languageName = languageName;
    }

    public TournamentChallenge getChallenge() {
        return challenge;
    }

    public String getLanguageName() {
        return languageName.getValue();
    }
}
