package org.rbernalop.apiexecution.shared.application.language.find;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class LanguageResponse {
    private String id;
    private String name;

    public LanguageResponse(Language language) {
        this.id = Objects.requireNonNull(language.getId()).getValue();
        this.name = language.getName();
    }
}
