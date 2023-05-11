package org.rbernalop.apitournament.tournament.domain.port;

import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentChallengeRepository extends JpaRepository<TournamentChallenge, ChallengeId> {
}
