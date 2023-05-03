package org.rbernalop.apitest.test.infrastructure.adapter;

import com.github.javafaker.Faker;
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

        String filesPath = Faker.instance().name().firstName();
        String scriptFileName = filesPath + "/Main.java";
        String testFileName = filesPath + "/MainTest.java";
        File folder = new File(filesPath);
        File scriptFile = new File(scriptFileName);
        File testFile = new File(testFileName);
        try {
            folder.mkdir();
            scriptFile.createNewFile();
            Files.writeString(scriptFile.toPath(), code);
            testFile.createNewFile();
            Files.writeString(testFile.toPath(), test);

            String compilationCommand = "javac -cp junit-platform-console-standalone-1.7.2.jar " + scriptFileName + " " + testFileName;
            Pair<String, Boolean> compilationResult = ShellRunner.executeCommand(compilationCommand);
            String compilationOutput = compilationResult.getFirst();
            boolean compilationSuccess = compilationResult.getSecond();
            if (!compilationSuccess) {
                throw new CompilationException(compilationOutput);
            }

            String executionCommand = "java -jar ../junit-platform-console-standalone-1.7.2.jar --class-path . -c MainTest";
            Pair<String, Boolean> executionResult = ShellRunner.executeCommand(executionCommand, filesPath);
            String executionOutput = executionResult.getFirst();
            boolean executionSuccess = executionResult.getSecond();

            runResult.setPassed(executionSuccess);
            runResult.setCompilationResult(compilationOutput);
            runResult.setExecutionResult(executionOutput);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        } finally {
            String removeFolderCommand = "rm -rf " + filesPath;
            Pair<String, Boolean> removeFolderResult = ShellRunner.executeCommand(removeFolderCommand);
            if (!removeFolderResult.getSecond()) {
                throw new FileException(removeFolderResult.getFirst());
            }
        }

        return runResult;
    }
}
