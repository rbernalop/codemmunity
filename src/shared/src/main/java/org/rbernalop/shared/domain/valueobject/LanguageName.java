package org.rbernalop.shared.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.exception.InvalidLanguageDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class LanguageName {
    private String languageName;

    public LanguageName(String languageName) {
        if (languageName == null || languageName.isBlank()) {
            throw new InvalidLanguageDataException("Language name cannot be empty");
        }
        this.languageName = languageName;
    }

    public String getValue() {
        return languageName;
    }
}
