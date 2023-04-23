package org.rbernalop.apitest.test.application.check;

import org.rbernalop.apitest.shared.application.test.TestResponse;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsResponse;
import org.rbernalop.apitest.test.domain.aggregate.Test;
import org.rbernalop.apitest.test.domain.port.TestRepository;
import org.rbernalop.apitest.test.domain.port.TestRunner;
import org.rbernalop.apitest.test.domain.port.TestRunnerFactory;
import org.rbernalop.apitest.test.domain.value_object.TestRunResult;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

import java.util.ArrayList;
import java.util.List;

public class TestChecker extends UseCase {
    private final TestRepository testRepository;

    public TestChecker(QueryBus queryBus, EventBus eventBus, TestRepository testRepository) {
        super(queryBus, eventBus);
        this.testRepository = testRepository;
    }

    public CheckScriptTestsResponse checkScript(ChallengeId challengeId, ScriptContent scriptContent, LanguageName languageName) {
        List<Test> tests = testRepository.findByChallengeIdAndLanguageName(challengeId, languageName);
        List<TestResponse> testsResults = new ArrayList<>();

        TestRunner testRunner = TestRunnerFactory.get(languageName.getValue());
        boolean passed = true;
        for (Test test : tests) {
            TestRunResult runResult = testRunner.run(scriptContent.getValue(), test.getTestContent());
            assert test.getId() != null;
            testsResults.add(TestResponse.from(test.getId().getValue(), test.getTestName(), runResult));
            passed = passed && runResult.getPassed();
        }

        return CheckScriptTestsResponse.from(testsResults, passed);
    }
}
