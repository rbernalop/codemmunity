package org.rbernalop.apichallenge.shared.application.challenge.finder.all;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Query;

@Getter
@Setter
public class FindChallengesPaginatedQuery implements Query {
    private int page;
    private int size;
}
