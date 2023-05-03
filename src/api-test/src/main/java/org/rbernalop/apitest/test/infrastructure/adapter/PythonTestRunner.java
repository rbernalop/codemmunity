package org.rbernalop.apitest.test.infrastructure.adapter;

import com.github.javafaker.Faker;
import org.rbernalop.apitest.test.domain.port.TestRunner;
import org.rbernalop.apitest.test.domain.value_object.TestRunResult;
import org.rbernalop.shared.domain.exception.FileException;
import org.rbernalop.shared.infrastructure.ShellRunner;
import org.springframework.data.util.Pair;

import java.io.File;
import java.nio.file.Files;

public class PythonTestRunner implements TestRunner {
    @Override
    public TestRunResult run(String code, String test) {
        TestRunResult runResult = new TestRunResult();

        String filesPath = Faker.instance().name().firstName();
        File folder = new File(filesPath);
        File scriptFile = new File(filesPath + "/Main.py");
        File testFile = new File(filesPath + "/Test.py");
        try {
            folder.mkdir();
            scriptFile.createNewFile();
            Files.writeString(scriptFile.toPath(), code);
            testFile.createNewFile();
            Files.writeString(testFile.toPath(), test);

            String executionCommand = "python3 Test.py";
            Pair<String, Boolean> result = ShellRunner.executeCommand(executionCommand, filesPath);
            String executionResult = result.getFirst();
            boolean executionSuccess = result.getSecond();
            runResult.setPassed(executionSuccess);
            runResult.setExecutionResult(executionResult);
        } catch (Exception e) {
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
