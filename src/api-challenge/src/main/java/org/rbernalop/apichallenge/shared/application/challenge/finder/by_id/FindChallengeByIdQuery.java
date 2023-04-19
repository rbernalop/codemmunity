package org.rbernalop.apichallenge.shared.application.challenge.finder.by_id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
public class FindChallengeByIdQuery implements Query {
    private String id;
}
