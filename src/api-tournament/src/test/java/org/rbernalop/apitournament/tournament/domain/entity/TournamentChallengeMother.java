package org.rbernalop.apitournament.tournament.domain.entity;

import org.rbernalop.apitournament.tournament.domain.value_object.*;

import java.util.List;

public class TournamentChallengeMother {
    public static TournamentChallenge random() {
        TournamentChallenge challenge = TournamentChallenge.create(ChallengeIdMother.random(), ChallengeTitleMother.random(),
                ChallengeDescriptionMother.random(), UserUsernameMother.random(), ChallengeDifficultyMother.random(),
                TournamentChallengeCategoryMother.random());
        List<TournamentChallengeBaseCode> baseCodes = List.of(TournamentChallengeBaseCodeMother.randomFromChallenge(challenge));
        challenge.setBaseCodes(baseCodes);
        return challenge;
    }
}