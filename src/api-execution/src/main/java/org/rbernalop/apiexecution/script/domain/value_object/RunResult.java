package org.rbernalop.apiexecution.script.domain.value_object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RunResult {
    private String compilationResult;
    private String executionResult;
}
