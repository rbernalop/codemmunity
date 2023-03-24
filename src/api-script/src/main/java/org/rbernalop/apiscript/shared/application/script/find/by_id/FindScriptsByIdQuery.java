package org.rbernalop.apiscript.shared.application.script.find.by_id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindScriptsByIdQuery implements Query {
    private String id;
}
