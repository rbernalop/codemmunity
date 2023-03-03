package org.rbernalop.apiscript.script.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.script.domain.valueobject.*;

@Entity
@Table(name = "scripts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Script {
    @EmbeddedId
    private ScriptId id;

    @Embedded
    private UserId ownerId;

    @Embedded
    private ScriptName name;

    @Embedded
    private ShareKey shareKey;

    @Embedded
    private ScriptLanguageId languageId;

    public Script(ScriptId id, UserId ownerId, ScriptName name, ShareKey shareKey, ScriptLanguageId languageId) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.shareKey = shareKey;
        this.languageId = languageId;
    }

    public String getId() {
        return id.getValue();
    }

    public String getOwnerId() {
        return ownerId.getValue();
    }

    public String getName() {
        return name.getName();
    }

    public String getShareKey() {
        return shareKey.getValue();
    }

    public String getLanguageId() {
        return languageId.getValue();
    }
}
