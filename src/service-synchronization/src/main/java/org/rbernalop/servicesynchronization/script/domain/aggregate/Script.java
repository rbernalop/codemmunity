package org.rbernalop.servicesynchronization.script.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.servicesynchronization.script.domain.value_object.ScriptContent;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.Identifier;
import org.rbernalop.shared.domain.valueobject.ScriptId;

@Entity
@Table(name = "scripts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Script extends AggregateRoot {
    @EmbeddedId
    private ScriptId id;

    @Embedded
    private ScriptContent content;

    public static Script create(ScriptId id, ScriptContent content) {
        Script script = new Script();
        script.id = id;
        script.content = content;
        return script;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getContent() {
        return content.getValue();
    }
}
