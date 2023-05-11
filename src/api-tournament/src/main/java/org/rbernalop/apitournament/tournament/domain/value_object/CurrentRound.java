package org.rbernalop.apitournament.tournament.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;

@NoArgsConstructor
public class CurrentRound {
    private int currentRound;

    public CurrentRound(int value) {
        if(value < 1)
            throw new InvalidTournamentDataException("Tournament current round must be greater than 0");
        if (value >= 10)
            throw new InvalidTournamentDataException("Tournament current round must be less than 10");
        this.currentRound = value;
    }

    public int getValue() {
        return currentRound;
    }
}
