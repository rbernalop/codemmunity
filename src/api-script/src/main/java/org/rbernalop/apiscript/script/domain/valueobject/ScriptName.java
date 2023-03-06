package org.rbernalop.apiscript.script.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ScriptName {
    private String name;

    public ScriptName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Script name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
