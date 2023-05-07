package org.rbernalop.apitournament.shared.application.tournament.join;

import com.github.javafaker.Faker;

public class JoinTournamentCommandMother {
    public static JoinTournamentCommand fromTournamentId(String tournamentId) {
        return new JoinTournamentCommand(
            tournamentId,
            Faker.instance().name().username()
        );
    }

    public static JoinTournamentCommand fromTournamentIdAndUser(String tournamentId, String creatorUsername) {
        return new JoinTournamentCommand(
            tournamentId,
            creatorUsername
        );
    }
}