package org.rbernalop.apiexecution.shared.application.language.find.by_id;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class FindLanguageByIdQuery implements Query {
    private String id;
}
