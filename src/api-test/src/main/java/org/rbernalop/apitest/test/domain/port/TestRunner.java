package org.rbernalop.apitest.test.domain.port;

import org.rbernalop.apitest.test.domain.value_object.TestRunResult;

public interface TestRunner {
    TestRunResult run(String script, String test);
}
