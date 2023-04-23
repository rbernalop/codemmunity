package org.rbernalop.apitest.test.infrastructure.adapter;

import org.rbernalop.apitest.test.domain.port.TestRunner;
import org.rbernalop.apitest.test.domain.value_object.TestRunResult;
import org.rbernalop.shared.domain.exception.CompilationException;
import org.rbernalop.shared.domain.exception.FileException;
import org.rbernalop.shared.infrastructure.ShellRunner;
import org.springframework.data.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JavaTestRunner implements TestRunner {
    @Override
    public TestRunResult run(String code, String test) {
        TestRunResult runResult = new TestRunResult();

        File scriptFile = new File("Main.java");
        File testFile = new File("MainTest.java");
        try {
            scriptFile.createNewFile();
            Files.writeString(scriptFile.toPath(), code);
            testFile.createNewFile();
            Files.writeString(testFile.toPath(), test);

            String compilationCommand = "javac -cp junit-platform-console-standalone-1.7.2.jar Main.java MainTest.java";
            Pair<String, Boolean> compilationResult = ShellRunner.executeCommand(compilationCommand);
            String compilationOutput = compilationResult.getFirst();
            boolean compilationSuccess = compilationResult.getSecond();
            if (!compilationSuccess) {
                throw new CompilationException(compilationOutput);
            }

            String executionCommand = "java -jar junit-platform-console-standalone-1.7.2.jar --class-path . -c MainTest";
            Pair<String, Boolean> executionResult = ShellRunner.executeCommand(executionCommand);
            String executionOutput = executionResult.getFirst();
            boolean executionSuccess = executionResult.getSecond();

            runResult.setPassed(executionSuccess);
            runResult.setCompilationResult(compilationOutput);
            runResult.setExecutionResult(executionOutput);
            // scriptFile.delete();
            // testFile.delete();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }

        return runResult;
    }
}
