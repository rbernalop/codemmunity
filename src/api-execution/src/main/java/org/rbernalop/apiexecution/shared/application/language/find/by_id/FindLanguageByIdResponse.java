package org.rbernalop.apiexecution.shared.application.language.find.by_id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.shared.domain.bus.query.Response;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FindLanguageByIdResponse implements Response {
    private String id;
    private String name;

    public FindLanguageByIdResponse(Language language) {
        assert language.getId() != null;
        this.id = language.getId().getValue();
        this.name = language.getName();
    }
}

