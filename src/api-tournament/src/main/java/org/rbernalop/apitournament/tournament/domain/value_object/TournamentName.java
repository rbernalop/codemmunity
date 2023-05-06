package org.rbernalop.apitournament.tournament.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;

@NoArgsConstructor
public class TournamentName {
    private String name;

    public TournamentName(String name) {
        if(name == null || name.isBlank()) {
            throw new InvalidTournamentDataException("Category name cannot be empty");
        }
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
