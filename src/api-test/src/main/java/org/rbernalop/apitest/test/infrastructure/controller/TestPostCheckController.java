package org.rbernalop.apitest.test.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apitest.shared.application.test.TestResponse;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsQuery;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsResponse;
import org.rbernalop.apitest.shared.application.test.send_results.SendTestsCompletionCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TestPostCheckController extends ApiController {
    public TestPostCheckController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("test/check")
    public TestPostCheckResponse handle(@RequestBody TestPostCheckRequest request,
            @RequestParam(value = "user") String userName) {
        CheckScriptTestsQuery query =
            new CheckScriptTestsQuery(request.getChallengeId(), request.getScriptContent(), request.getLanguageName());
        CheckScriptTestsResponse response = ask(query);

        SendTestsCompletionCommand command = new SendTestsCompletionCommand(
            response.isPassed(), userName, request.getChallengeId(), request.getScriptContent(), request.getLanguageName()
        );
        dispatch(command);

        return TestPostCheckResponse.from(response);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class TestPostCheckRequest {
    private String challengeId;
    private String scriptContent;
    private String languageName;
}

@Getter
@Setter
class TestPostCheckResponse {
    private List<TestPostCheckResult> results;
    private Boolean passed;

    public static TestPostCheckResponse from(CheckScriptTestsResponse response) {
        TestPostCheckResponse result = new TestPostCheckResponse();
        result.setResults(response.getResults().stream().map(TestPostCheckResult::from).toList());
        result.setPassed(response.isPassed());
        return result;
    }
}

@Getter
@Setter
class TestPostCheckResult {
    private String testId;
    private String testName;
    private String compilationResult;
    private String executionResult;
    private Boolean passed;

    public static TestPostCheckResult from(TestResponse testResponse) {
        TestPostCheckResult result = new TestPostCheckResult();
        result.setTestId(testResponse.getTestId());
        result.setTestName(testResponse.getTestName());
        result.setCompilationResult(testResponse.getCompilationResult());
        result.setExecutionResult(testResponse.getExecutionResult());
        result.setPassed(testResponse.getPassed());
        return result;
    }
}