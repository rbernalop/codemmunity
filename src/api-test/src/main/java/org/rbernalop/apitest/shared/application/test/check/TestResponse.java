package org.rbernalop.apitest.shared.application.test.check;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitest.test.domain.value_object.TestRunResult;

@Getter
@Setter
public class TestResponse {
    private String testId;
    private String testName;
    private String compilationResult;
    private String executionResult;
    private Boolean passed;

    public static TestResponse from(String testId, String testName, TestRunResult runResult) {
        TestResponse testResponse = new TestResponse();
        testResponse.setTestId(testId);
        testResponse.setTestName(testName);
        testResponse.setCompilationResult(runResult.getCompilationResult());
        testResponse.setExecutionResult(runResult.getExecutionResult());
        testResponse.setPassed(runResult.getPassed());
        return testResponse;
    }
}
