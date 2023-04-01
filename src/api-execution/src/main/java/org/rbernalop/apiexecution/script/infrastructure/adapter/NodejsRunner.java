package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;

import java.io.File;
import java.nio.file.Files;

public class NodejsRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        File file = new File("Main.js");
        try {
            file.createNewFile();
            Files.writeString(file.toPath(), code);
            String executionCommand = "node Main.js";
            String executionResult = ShellRunner.executeCommand(executionCommand).getFirst();
            runResult.setExecutionResult(executionResult);

            file.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return runResult;
    }
}
