package org.rbernalop.apitournament.tournament.domain.value_object;

import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallengeBaseCode;

public class TournamentChallengeBaseCodeMother {
    public static TournamentChallengeBaseCode randomFromChallenge(TournamentChallenge challenge) {
        TournamentChallengeBaseCodeId id = new TournamentChallengeBaseCodeId(challenge, LanguageNameMother.random());
        return TournamentChallengeBaseCode.create(id, ScriptContentMother.random());
    }
}
