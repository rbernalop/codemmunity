package org.rbernalop.apiscript.script.domain.value_object;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ScriptId extends Identifier {

    private String id;

    public ScriptId(String id) {
        super(id);
        this.id = id;
    }

    @Override
    public String getValue() {
        return id;
    }
}
