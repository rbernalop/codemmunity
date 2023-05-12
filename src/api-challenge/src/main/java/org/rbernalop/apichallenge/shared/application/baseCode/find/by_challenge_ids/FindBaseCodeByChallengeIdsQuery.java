package org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindBaseCodeByChallengeIdsQuery implements Query {
    private List<String> challengeIds;
}
