package org.rbernalop.apichallenge.completion.domain.port;

import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletionRepository extends JpaRepository<Completion, CompletionId> {
    List<Completion> findByChallengeIdInAndUserUsername(List<ChallengeId> challengeIDs, UserUsername username);
}
