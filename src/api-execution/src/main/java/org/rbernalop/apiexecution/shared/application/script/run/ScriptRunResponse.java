package org.rbernalop.apiexecution.shared.application.script.run;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Response;

@Getter
@Setter
@EqualsAndHashCode
public class ScriptRunResponse implements Response {
    private String compilationOutput;
    private String executionOutput;
}
