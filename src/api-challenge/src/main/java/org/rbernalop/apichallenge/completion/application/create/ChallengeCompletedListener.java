package org.rbernalop.apichallenge.completion.application.create;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.shared.domain.EventListener;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.event.test.ChallengeCompletedDomainEvent;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Service
@Slf4j
public class ChallengeCompletedListener {
    private final CompletionCreator completionCreator;

    public ChallengeCompletedListener(QueryBus queryBus, EventBus eventBus, CompletionRepository completionRepository) {
        this.completionCreator = new CompletionCreator(queryBus, eventBus, completionRepository);
    }

    @EventListener
    public void handle(ChallengeCompletedDomainEvent event) {
        log.info("Challenge completed event received for challenge {} and user {}", event.getAggregateId(), event.getUsername());

        ChallengeId challengeId = new ChallengeId(event.getAggregateId());
        UserUsername userUsername = new UserUsername(event.getUsername());
        LanguageName languageName = new LanguageName(event.getLanguageName());
        ScriptContent scriptContent = new ScriptContent(event.getScriptContent());
        completionCreator.create(challengeId, userUsername, languageName, scriptContent);
    }

}
