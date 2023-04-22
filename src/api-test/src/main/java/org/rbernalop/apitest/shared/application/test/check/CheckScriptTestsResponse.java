package org.rbernalop.apitest.shared.application.test.check;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@Getter
@Setter
public class CheckScriptTestsResponse implements Response {
    private List<TestResponse> results;
    private boolean passed;

    public static CheckScriptTestsResponse from(List<TestResponse> results, boolean passed) {
        CheckScriptTestsResponse response = new CheckScriptTestsResponse();
        response.setResults(results);
        response.setPassed(passed);
        return response;
    }
}
