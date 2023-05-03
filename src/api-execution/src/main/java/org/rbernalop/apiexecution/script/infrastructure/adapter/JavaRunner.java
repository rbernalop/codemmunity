package org.rbernalop.apiexecution.script.infrastructure.adapter;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.exception.CompilationException;
import org.rbernalop.apiexecution.script.domain.exception.ExecutionException;
import org.rbernalop.shared.domain.exception.FileException;
import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.shared.infrastructure.ShellRunner;
import org.springframework.data.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        Pair<String, Boolean> result;
        String filesPath = Faker.instance().name().firstName();
        String scriptFileName = filesPath + "/Main.java";
        File folder = new File(filesPath);
        File file = new File(scriptFileName);
        try {
            folder.mkdir();
            file.createNewFile();
            Files.writeString(file.toPath(), code);
            String compilationCommand = "javac " + scriptFileName;
            result = ShellRunner.executeCommand(compilationCommand);
            String compilationResult = result.getFirst();
            boolean compilationSuccess = result.getSecond();
            if(!compilationSuccess) {
                throw new CompilationException(compilationResult);
            }
            runResult.setCompilationResult(compilationResult);

            String executionCommand = "java -classpath " + filesPath + " Main";
            result = ShellRunner.executeCommand(executionCommand);
            String executionResult = result.getFirst();
            boolean executionSuccess = result.getSecond();
            if(!executionSuccess) {
                throw new ExecutionException(executionResult);
            }
            runResult.setExecutionResult(executionResult);

        } catch (IOException e) {
            throw new FileException(e.getMessage());
        } finally {
            String removeCommand = "rm -rf " + filesPath;
            result = ShellRunner.executeCommand(removeCommand);
            if(!result.getSecond()) {
                throw new FileException(result.getFirst());
            }
        }

        return runResult;
    }
}
