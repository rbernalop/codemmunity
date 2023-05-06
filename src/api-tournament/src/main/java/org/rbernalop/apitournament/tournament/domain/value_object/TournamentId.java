package org.rbernalop.apitournament.tournament.domain.value_object;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class TournamentId extends Identifier {
    private String tournamentId;

    public TournamentId(String value) {
        this.tournamentId = value;
    }

    public String getValue() {
        return tournamentId;
    }
}
