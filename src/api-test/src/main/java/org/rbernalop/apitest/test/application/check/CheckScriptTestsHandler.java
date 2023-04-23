package org.rbernalop.apitest.test.application.check;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsQuery;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsResponse;
import org.rbernalop.apitest.test.domain.port.TestRepository;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

@Service
@Slf4j
public class CheckScriptTestsHandler implements QueryHandler<CheckScriptTestsQuery, CheckScriptTestsResponse> {

    private final TestChecker testChecker;

    public CheckScriptTestsHandler(QueryBus queryBus, EventBus eventBus, TestRepository testRepository) {
        this.testChecker = new TestChecker(queryBus, eventBus, testRepository);
    }

    @Override
    public CheckScriptTestsResponse handle(CheckScriptTestsQuery query) {
        log.info("Checking script for challenge {} in language {}", query.getChallengeId(), query.getLanguageName());
        ChallengeId challengeId = new ChallengeId(query.getChallengeId());
        ScriptContent scriptContent = new ScriptContent(query.getScriptContent());
        LanguageName languageName = new LanguageName(query.getLanguageName());
        return testChecker.checkScript(challengeId, scriptContent, languageName);
    }
}
