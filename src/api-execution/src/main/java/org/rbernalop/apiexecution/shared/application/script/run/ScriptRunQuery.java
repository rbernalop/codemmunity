package org.rbernalop.apiexecution.shared.application.script.run;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ScriptRunQuery implements Query {
    private String script;
    private String languageId;
}
