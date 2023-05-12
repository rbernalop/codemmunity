package org.rbernalop.apichallenge.shared.application.baseCode.find.by_id;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindBaseCodeByIdQuery implements Query {
    private String challengeId;
    private String languageName;
}
