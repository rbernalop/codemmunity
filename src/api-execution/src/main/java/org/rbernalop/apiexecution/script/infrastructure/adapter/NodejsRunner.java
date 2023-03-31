package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;

public class NodejsRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        String command = "node -e \"" + code + "\"";
        String exectionResult = ShellRunner.executeCommand(command);
        runResult.setExecutionResult(exectionResult);

        return runResult;
    }
}
