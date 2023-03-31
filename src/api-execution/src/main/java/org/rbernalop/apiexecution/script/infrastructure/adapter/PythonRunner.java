package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;

public class PythonRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        String command = "python -c \"" + code + "\"";
        String executionCommand = ShellRunner.executeCommand(command);
        runResult.setExecutionResult(executionCommand);

        return runResult;
    }
}
