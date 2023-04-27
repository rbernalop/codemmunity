package org.rbernalop.apichallenge.shared.application.completion.find.by_ids;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FindUserCompletionsByIdsQuery implements Query {
    private List<String> ids;
    private String username;
}
