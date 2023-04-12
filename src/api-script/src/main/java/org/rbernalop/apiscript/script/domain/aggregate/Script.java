package org.rbernalop.apiscript.script.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.script.domain.value_object.*;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.UserUsername;

@Entity
@Table(name = "scripts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Script extends AggregateRoot {
    @EmbeddedId
    private ScriptId id;

    @Embedded
    private UserUsername userUsername;

    @Embedded
    private ScriptName name;

    @Embedded
    private ShareKey shareKey;

    @Embedded
    private LanguageId languageId;

    public static Script create(ScriptId id, UserUsername userUsername, ScriptName name, ShareKey key, LanguageId languageId) {
        Script script = new Script();
        script.id = id;
        script.userUsername = userUsername;
        script.name = name;
        script.shareKey = key;
        script.languageId = languageId;
        return script;
    }

    public ScriptId getId() {
        return id;
    }

    public String getOwnerName() {
        return userUsername.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getShareKey() {
        return shareKey.getValue();
    }

    public String getLanguageId() {
        return languageId.getValue();
    }

    public void rename(ScriptName nextName) {
        this.name = nextName;
    }

    public void renewShareKey(ShareKey shareKey) {
        this.shareKey = shareKey;
    }

    public void changeLanguage(LanguageId languageId) {
        this.languageId = languageId;
    }

    public boolean alreadyHasLanguage(LanguageId languageId) {
        return this.languageId.equals(languageId);
    }
}
