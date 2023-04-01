package org.rbernalop.apiexecution.script.infrastructure.adapter;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.ShellRunner;

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
            String compilationResult = ShellRunner.executeCommand(compilationCommand).getFirst();
            runResult.setCompilationResult(compilationResult);

            String executionCommand = "java Main";
            String executionResult = ShellRunner.executeCommand(executionCommand).getFirst();
            runResult.setExecutionResult(executionResult);

            file.delete();
            File classFile = new File("Main.class");
            classFile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return runResult;
    }
}
