package org.rbernalop.apitournament.tournament.domain.value_object;

import lombok.NoArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
public class TournamentBeginningDate {
    private Date beginningDate;

    public TournamentBeginningDate(Date beginningDate) {
        if(beginningDate == null) {
            throw new InvalidTournamentDataException("Beginning date cannot be empty");
        }

        if (beginningDate.before(Date.from(Instant.now()))) {
            throw new InvalidTournamentDataException("Beginning date cannot be in the past");
        }

        this.beginningDate = beginningDate;
    }

    public Date getValue() {
        return beginningDate;
    }
}
