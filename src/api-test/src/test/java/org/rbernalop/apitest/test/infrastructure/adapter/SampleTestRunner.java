package org.rbernalop.apitest.test.infrastructure.adapter;

import org.rbernalop.apitest.test.domain.port.TestRunner;
import org.rbernalop.apitest.test.domain.value_object.TestRunResult;

public class SampleTestRunner implements TestRunner {
    @Override
    public TestRunResult run(String script, String test) {
        TestRunResult testRunResult = new TestRunResult();
        testRunResult.setPassed(true);
        testRunResult.setCompilationResult("Compilation result");
        testRunResult.setExecutionResult("Execution result");
        return testRunResult;
    }
}
