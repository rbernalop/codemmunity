package org.rbernalop.apitournament.tournament.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;

@NoArgsConstructor
public class TournamentRounds {
    private int rounds;

    public TournamentRounds(int value) {
        if(value < 1)
            throw new InvalidTournamentDataException("Tournament size must be greater than 0");
        if (value >= 10)
            throw new InvalidTournamentDataException("Tournament size must be less than 10");
        this.rounds = value;
    }

    public int getValue() {
        return rounds;
    }
}
