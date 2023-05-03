package org.rbernalop.apiexecution.script.infrastructure.adapter;

import com.github.javafaker.Faker;
import org.rbernalop.apiexecution.script.domain.exception.ExecutionException;
import org.rbernalop.shared.domain.exception.FileException;
import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.shared.infrastructure.ShellRunner;
import org.springframework.data.util.Pair;

import java.io.File;
import java.nio.file.Files;

public class PythonRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        String fileName = Faker.instance().name().firstName() + ".py";
        File file = new File(fileName);
        try {
            file.createNewFile();
            Files.writeString(file.toPath(), code);
            String executionCommand = "python3 " + fileName;
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
