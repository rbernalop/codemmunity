package org.rbernalop.apichallenge.completion.domain.port;

import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletionRepository extends JpaRepository<Completion, CompletionId> {
}
