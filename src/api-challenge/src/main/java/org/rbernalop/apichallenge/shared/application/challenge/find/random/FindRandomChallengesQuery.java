package org.rbernalop.apichallenge.shared.application.challenge.find.random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
public class FindRandomChallengesQuery implements Query {
    private int numChallenges;
}
