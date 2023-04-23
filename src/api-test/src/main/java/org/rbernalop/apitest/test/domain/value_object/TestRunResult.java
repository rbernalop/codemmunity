package org.rbernalop.apitest.test.domain.value_object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestRunResult {
    private String compilationResult;
    private String executionResult;
    private Boolean passed;
}
