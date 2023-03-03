package org.rbernalop.apiscript.script.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ScriptLanguageId {
    private String languageId;

    public ScriptLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageId() {
        return languageId;
    }
}
