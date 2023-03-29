package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.domain.value_object.RunResult;

public class MockRunner implements ScriptRunner {
    @Override
    public RunResult run(String script) {
        return new RunResult("Compilation result", "Execution result");
    }
}
