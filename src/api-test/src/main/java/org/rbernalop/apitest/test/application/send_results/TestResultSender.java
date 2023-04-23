package org.rbernalop.apitest.test.application.send_results;

import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.event.test.ChallengeCompletedDomainEvent;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

public class TestResultSender extends UseCase {
    public TestResultSender(QueryBus queryBus, EventBus eventBus) {
        super(queryBus, eventBus);
    }

    public void sendResultsIfCorrect(
            boolean passed,
            UserUsername userUsername,
            ChallengeId challengeId,
            ScriptContent scriptContent,
            LanguageName languageName) {
        if (passed) {
            ChallengeCompletedDomainEvent event = new ChallengeCompletedDomainEvent(
                    userUsername.getValue(),
                    challengeId.getValue(),
                    scriptContent.getValue(),
                    languageName.getValue()
            );
            eventBus.publish(List.of(event));
        }
    }
}
