package org.rbernalop.servicesynchronization.script.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.ShareKey;

@Entity
@Table(name = "scripts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Script extends AggregateRoot {
    @EmbeddedId
    private ScriptId id;

    @Embedded
    private ShareKey shareKey;

    @Embedded
    private ScriptContent content;

    public static Script create(ScriptId id, ShareKey shareKey, ScriptContent content) {
        Script script = new Script();
        script.id = id;
        script.shareKey = shareKey;
        script.content = content;
        return script;
    }

    @Override
    public ScriptId getId() {
        return id;
    }

    public ShareKey getShareKey() {
        return shareKey;
    }

    public String getContent() {
        return content.getValue();
    }

    public void changeContent(ScriptContent content) {
        this.content = content;
    }
}
