package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class LanguageId extends Identifier {
    private String languageId;

    public LanguageId(String languageId) {
        super(languageId);
        this.languageId = languageId;
    }

    @Override
    public String getValue() {
        return languageId;
    }
}
