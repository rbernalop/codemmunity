package org.rbernalop.apiscript.shared.application.script.find.by_userid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.query.Query;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindScriptsByUsernameQuery implements Query {
    private String userUsername;
    private int page;
    private int size;
}
