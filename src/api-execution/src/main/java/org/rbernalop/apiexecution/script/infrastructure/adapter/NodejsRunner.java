package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.exception.ExecutionException;
import org.rbernalop.apiexecution.script.domain.exception.FileException;
import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;
import org.springframework.data.util.Pair;

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
            Pair<String, Boolean> result = ShellRunner.executeCommand(executionCommand);
            String executionResult = result.getFirst();
            boolean executionSuccess = result.getSecond();
            if(!executionSuccess) {
                throw new ExecutionException(executionResult);
            }
            runResult.setExecutionResult(executionResult);

            file.delete();
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
        return runResult;
    }
}
