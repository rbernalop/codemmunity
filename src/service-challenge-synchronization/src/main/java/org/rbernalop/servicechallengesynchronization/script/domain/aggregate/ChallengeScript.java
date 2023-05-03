package org.rbernalop.servicechallengesynchronization.script.domain.aggregate;


import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.Identifier;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

@Entity
@Table(name = "challenge_script")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class ChallengeScript extends AggregateRoot {
    @EmbeddedId
    private ChallengeScriptId id;

    @Embedded
    private ScriptContent scriptContent;

    @Embedded
    private LanguageName languageName;

    public static ChallengeScript create(ChallengeScriptId challengeScriptId, LanguageName languageName, ScriptContent scriptContent) {
        ChallengeScript completion = new ChallengeScript();
        completion.id = challengeScriptId;
        completion.languageName = languageName;
        completion.scriptContent = scriptContent;
        return completion;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    public ChallengeScriptId getChallengeScriptId() {
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

    public void updateContent(ScriptContent scriptContent) {
        this.scriptContent = scriptContent;
    }

    public void updateLanguageName(LanguageName languageName) {
        this.languageName = languageName;
    }

    public void update(ChallengeScript storedScript) {
        this.updateContent(storedScript.scriptContent);
        this.updateLanguageName(storedScript.languageName);
    }
}
