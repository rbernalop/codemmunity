package org.rbernalop.apitournament.tournament.domain.entity;

import org.rbernalop.apitournament.tournament.domain.value_object.*;

public class TournamentChallengeMother {
    public static TournamentChallenge random() {
        return TournamentChallenge.create(ChallengeIdMother.random(), ChallengeTitleMother.random(),
                ChallengeDescriptionMother.random(), UserUsernameMother.random(), ChallengeDifficultyMother.random(),
                TournamentChallengeCategoryMother.random());
    }
}