package org.rbernalop.apiexecution.script.infrastructure.adapter;

import com.github.javafaker.Faker;
import org.rbernalop.apiexecution.script.domain.value_object.RunResult;
import org.rbernalop.apiexecution.script.domain.port.ScriptRunner;
import org.rbernalop.apiexecution.script.infrastructure.util.CommandLineRunner;

public class JavaRunner implements ScriptRunner {
    @Override
    public RunResult run(String code) {
        RunResult runResult = new RunResult();
        String fileName = Faker.instance().name().firstName();

        String createFileCommand = "touch " + fileName + ".java && echo \"" + code + "\" > " + fileName + ".java";
        CommandLineRunner.executeCommand(createFileCommand);

        String compilationCommand = "javac " + fileName + ".java";
        String compilationResult = CommandLineRunner.executeCommand(compilationCommand);
        runResult.setCompilationResult(compilationResult);

        String executionCommand = "java " + fileName;
        String executionResult = CommandLineRunner.executeCommand(executionCommand);
        runResult.setExecutionResult(executionResult);

        String cleanCommand = "rm " + fileName + ".java && rm " + fileName + ".class";
        CommandLineRunner.executeCommand(cleanCommand);

        return runResult;
    }
}
