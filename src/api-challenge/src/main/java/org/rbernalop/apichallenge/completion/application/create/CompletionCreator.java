package org.rbernalop.apichallenge.completion.application.create;

import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.UserUsername;

public class CompletionCreator extends UseCase {
    private final CompletionRepository completionRepository;

    public CompletionCreator(QueryBus queryBus, EventBus eventBus, CompletionRepository completionRepository) {
        super(queryBus, eventBus);
        this.completionRepository = completionRepository;
    }

    public void execute(ChallengeId challengeId, UserUsername userUsername, LanguageName languageName, ScriptContent scriptContent) {
        CompletionId completionId = new CompletionId(challengeId, userUsername);
        Completion completion = Completion.create(completionId, languageName, scriptContent);
        completionRepository.save(completion);
    }
}
