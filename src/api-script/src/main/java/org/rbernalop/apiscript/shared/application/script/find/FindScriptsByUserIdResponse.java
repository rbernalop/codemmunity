package org.rbernalop.apiscript.shared.application.script.find;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class FindScriptsByUserIdResponse implements Response {
    public List<ScriptResponse> scriptsResponses;

    public FindScriptsByUserIdResponse(List<Script> scripts) {
        this.scriptsResponses = scripts.stream().map(ScriptResponse::new).toList();
    }
}
