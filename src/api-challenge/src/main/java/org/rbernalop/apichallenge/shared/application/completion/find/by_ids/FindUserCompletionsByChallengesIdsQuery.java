package org.rbernalop.apichallenge.shared.application.completion.find.by_ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Query;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class FindUserCompletionsByChallengesIdsQuery implements Query {
    private List<String> ids;
    private String username;
}
