package org.rbernalop.apitournament.tournament.domain.port;

import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;

import java.util.List;

public interface RandomChallengeRetriever {
    List<TournamentChallenge> getRandomChallenges(int numberOfChallenges);
}
