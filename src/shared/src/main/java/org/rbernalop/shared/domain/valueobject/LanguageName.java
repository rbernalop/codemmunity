package org.rbernalop.shared.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.exception.InvalidLanguageDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class LanguageName {
    private String name;

    public LanguageName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidLanguageDataException("Language name cannot be empty");
        }
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
