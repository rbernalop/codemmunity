package org.rbernalop.apiscript.script.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ScriptId extends Identifier {
    public ScriptId(String value) {
        super(value);
    }
}
