package org.rbernalop.apichallenge.challenge.domain.port;

import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, ChallengeId> {

}
