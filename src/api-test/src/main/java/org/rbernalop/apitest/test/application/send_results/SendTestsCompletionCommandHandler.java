package org.rbernalop.apitest.test.application.send_results;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apitest.shared.application.test.send_results.SendTestsCompletionCommand;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class SendTestsCompletionCommandHandler implements CommandHandler<SendTestsCompletionCommand> {
    private final TestResultSender testResultSender;

    public SendTestsCompletionCommandHandler(QueryBus queryBus, EventBus eventBus) {
        this.testResultSender = new TestResultSender(queryBus, eventBus);
    }

    @Override
    public void handle(SendTestsCompletionCommand command) {
        log.info("Sending test results to challenge API for challenge with id: {}", command.getChallengeId());
        boolean passed = command.isPassed();
        UserUsername userUsername = new UserUsername(command.getUsername());
        ChallengeId challengeId = new ChallengeId(command.getChallengeId());
        ScriptContent scriptContent = new ScriptContent(command.getScriptContent());
        LanguageName languageName = new LanguageName(command.getLanguageName());

        testResultSender.sendResultsIfCorrect(passed, userUsername, challengeId, scriptContent, languageName);
    }
}
