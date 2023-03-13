package org.rbernalop.apiscript.script.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.script.domain.exception.InvalidScriptDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class ScriptName {
    private String name;

    public ScriptName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidScriptDataException("Script name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
