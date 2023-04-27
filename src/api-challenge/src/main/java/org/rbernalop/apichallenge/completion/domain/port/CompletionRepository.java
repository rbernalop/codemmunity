package org.rbernalop.apichallenge.completion.domain.port;

import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CompletionRepository extends JpaRepository<Completion, CompletionId> {
    @Query("SELECT c FROM Completion c WHERE c.id.challengeId IN :challengeIds AND c.id.userUsername = :userUsername")
    List<Completion> findByChallengeIdInAndUserUsername(@Param("challengeIds") Collection<ChallengeId> challengeIds, @Param("userUsername") UserUsername userUsername);
}
