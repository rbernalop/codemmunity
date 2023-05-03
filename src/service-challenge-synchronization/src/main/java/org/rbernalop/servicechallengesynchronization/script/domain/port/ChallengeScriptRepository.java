package org.rbernalop.servicechallengesynchronization.script.domain.port;

import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeScriptRepository extends JpaRepository<ChallengeScript, ChallengeScriptId> {
}
