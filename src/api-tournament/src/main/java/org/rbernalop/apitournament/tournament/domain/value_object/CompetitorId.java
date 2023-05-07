package org.rbernalop.apitournament.tournament.domain.value_object;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.aggregate.Tournament;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class CompetitorId implements Serializable{
    private UserUsername competitorUsername;

    @ManyToOne
    private Tournament tournament;

    public CompetitorId(UserUsername competitorUsername, Tournament tournament) {
        this.competitorUsername = competitorUsername;
        this.tournament = tournament;
    }

    public String getCopetitorUsername() {
        return competitorUsername.getValue();
    }

    public Tournament getTournament() {
        return tournament;
    }
}
