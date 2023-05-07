package org.rbernalop.apitournament.tournament.domain.value_object;


import java.time.Instant;
import java.util.Date;

public class TournamentBeginningDateMother {
    private static final int DAY_IN_SECONDS = 60 * 60 * 24;

    public static TournamentBeginningDate random() {
        return new TournamentBeginningDate(Date.from(Instant.now().plusSeconds(DAY_IN_SECONDS)));
    }
}
