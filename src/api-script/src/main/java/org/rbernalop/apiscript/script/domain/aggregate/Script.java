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

@Entity
@Table(name = "scripts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Script extends AggregateRoot {
    @EmbeddedId
    private ScriptId id;

    @Embedded
    private OwnerUsername ownerUsername;

    @Embedded
    private ScriptName name;

    @Embedded
    private ShareKey shareKey;

    @Embedded
    private LanguageId languageId;

    public Script(ScriptId id, OwnerUsername ownerUsername, ScriptName name, ShareKey shareKey, LanguageId languageId) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.shareKey = shareKey;
        this.languageId = languageId;
    }

    public static Script create(ScriptId id, ShareKey key, LanguageId languageId, OwnerUsername ownerUserName) {
        return new Script(id, ownerUserName, new ScriptName("Untitled script"), key, languageId);
    }

    public ScriptId getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerUsername.getValue();
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
