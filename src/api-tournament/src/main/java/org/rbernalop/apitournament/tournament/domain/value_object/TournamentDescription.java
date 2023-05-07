package org.rbernalop.apitournament.tournament.domain.value_object;

import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;

@NoArgsConstructor
public class TournamentDescription {
    @Column(columnDefinition = "TEXT")
    private String description;

    public TournamentDescription(String description) {
        if(description == null || description.isBlank()) {
            throw new InvalidTournamentDataException("Challenge description cannot be empty");
        }
        this.description = description;
    }

    public String getValue() {
        return description;
    }
}
