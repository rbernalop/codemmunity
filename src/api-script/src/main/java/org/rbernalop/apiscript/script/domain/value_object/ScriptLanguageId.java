package org.rbernalop.apiscript.script.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@NoArgsConstructor
@EqualsAndHashCode
public class ScriptLanguageId extends Identifier {
    private String languageId;

    public ScriptLanguageId(String languageId) {
        super(languageId);
        this.languageId = languageId;
    }

    @Override
    public String getValue() {
        return languageId;
    }
}
