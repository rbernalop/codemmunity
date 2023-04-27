package org.rbernalop.apichallenge.completion.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.*;

@Entity
@Table(name = "completions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Completion extends AggregateRoot {
    @EmbeddedId
    private CompletionId id;

    @Embedded
    private ScriptContent scriptContent;

    @Embedded
    private LanguageName languageName;

    public static Completion create(CompletionId completionId, LanguageName languageName, ScriptContent scriptContent) {
        Completion completion = new Completion();
        completion.id = completionId;
        completion.languageName = languageName;
        completion.scriptContent = scriptContent;
        return completion;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    public CompletionId getCompletionId() {
        return id;
    }

    public String getChallengeId() {
        return id.getChallengeId();
    }

    public String getUserUsername() {
        return id.getUserUsername();
    }

    public String getScriptContent() {
        return scriptContent.getValue();
    }

    public String getLanguageName() {
        return languageName.getValue();
    }
}
