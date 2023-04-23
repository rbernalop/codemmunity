package org.rbernalop.apitest.test.infrastructure.adapter;

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

        File scriptFile = new File("Main.py");
        File testFile = new File("Test.py");
        try {
            scriptFile.createNewFile();
            Files.writeString(scriptFile.toPath(), code);
            testFile.createNewFile();
            Files.writeString(testFile.toPath(), test);

            String executionCommand = "python3 -m unittest Test.py";
            Pair<String, Boolean> result = ShellRunner.executeCommand(executionCommand);
            String executionResult = result.getFirst();
            boolean executionSuccess = result.getSecond();
            runResult.setPassed(executionSuccess);
            runResult.setExecutionResult(executionResult);

            scriptFile.delete();
            testFile.delete();
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
        return runResult;
    }
}
