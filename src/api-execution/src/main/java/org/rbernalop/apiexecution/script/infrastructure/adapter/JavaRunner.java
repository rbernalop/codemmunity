package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.exception.CompilationException;
import org.rbernalop.apiexecution.script.domain.exception.ExecutionException;
import org.rbernalop.apiexecution.script.domain.exception.FileException;
import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;
import org.springframework.data.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();

        File file = new File("Main.java");
        try {
            file.createNewFile();
            Files.writeString(file.toPath(), code);
            String compilationCommand = "javac Main.java";
            Pair<String, Boolean> result = ShellRunner.executeCommand(compilationCommand);
            String compilationResult = result.getFirst();
            boolean compilationSuccess = result.getSecond();
            if(!compilationSuccess) {
                throw new CompilationException(compilationResult);
            }
            runResult.setCompilationResult(compilationResult);

            String executionCommand = "java Main";
            result = ShellRunner.executeCommand(executionCommand);
            String executionResult = result.getFirst();
            boolean executionSuccess = result.getSecond();
            if(!executionSuccess) {
                throw new ExecutionException(executionResult);
            }
            runResult.setExecutionResult(executionResult);

            file.delete();
            File classFile = new File("Main.class");
            classFile.delete();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }

        return runResult;
    }
}
