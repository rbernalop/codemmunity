package org.rbernalop.apiscript.shared.application.script.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindScriptsByUserIdQuery implements Query {
    private String ownerUsername;
    private int page;
    private int size;
}
