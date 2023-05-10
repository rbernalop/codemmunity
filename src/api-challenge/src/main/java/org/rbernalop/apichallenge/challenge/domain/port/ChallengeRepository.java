package org.rbernalop.apichallenge.challenge.domain.port;

import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, ChallengeId> {
    @Query(value = "SELECT * FROM challenges ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
    List<Challenge> findRandomChallenges(int numChallenges);
}
