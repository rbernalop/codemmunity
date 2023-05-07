package org.rbernalop.apitournament.tournament.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;

@NoArgsConstructor
public class TournamentSize {
    private int size;

    public TournamentSize(int value) {
        if(value < 2)
            throw new InvalidTournamentDataException("Tournament size must be greater than 2");
        if (value > 15)
            throw new InvalidTournamentDataException("Tournament size must be less than 15");
        this.size = value;
    }

    public int getValue() {
        return size;
    }
}
