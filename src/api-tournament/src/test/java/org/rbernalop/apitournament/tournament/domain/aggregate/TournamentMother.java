package org.rbernalop.apitournament.tournament.domain.aggregate;

import org.rbernalop.apitournament.tournament.domain.value_object.*;

import java.util.List;

public class TournamentMother {
    public static Tournament random() {
        return Tournament.create(
            TournamentIdMother.random(),
            TournamentNameMother.random(),
            TournamentDescriptionMother.random(),
            UserUsernameMother.random(),
            TournamentBeginningDateMother.random(),
            TournamentSizeMother.random(),
            List.of(),
            TournamentRoundsMother.random()
        );
    }

    public static Tournament randomFromSize(int size) {
        return Tournament.create(
            TournamentIdMother.random(),
            TournamentNameMother.random(),
            TournamentDescriptionMother.random(),
            UserUsernameMother.random(),
            TournamentBeginningDateMother.random(),
            TournamentSizeMother.fromSize(size),
            List.of(),
            TournamentRoundsMother.random()
        );
    }
}