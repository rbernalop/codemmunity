package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.CommandLineRunner;

public class NodejsRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        String command = "node -e \"" + code + "\"";
        String exectionResult = CommandLineRunner.executeCommand(command);
        runResult.setExecutionResult(exectionResult);

        return runResult;
    }
}
