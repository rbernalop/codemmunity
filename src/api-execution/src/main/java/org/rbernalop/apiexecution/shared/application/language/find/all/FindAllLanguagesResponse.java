package org.rbernalop.apiexecution.shared.application.language.find.all;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.shared.application.language.find.LanguageResponse;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class FindAllLanguagesResponse implements Response {
    private List<LanguageResponse> languages;

    public FindAllLanguagesResponse(List<Language> language) {
        this.languages = language.stream().map(LanguageResponse::new).toList();
    }
}

