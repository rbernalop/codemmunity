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

    public Script(ScriptId id, ScriptContent content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public String getContent() {
        return content.getValue();
    }
}
