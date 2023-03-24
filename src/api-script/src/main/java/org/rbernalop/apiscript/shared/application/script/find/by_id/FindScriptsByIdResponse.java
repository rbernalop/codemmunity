package org.rbernalop.apiscript.shared.application.script.find.by_id;

import lombok.*;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.shared.domain.bus.query.Response;

@Getter
@Setter
@EqualsAndHashCode
public class FindScriptsByIdResponse implements Response {
    private String id;
    private String name;
    private String languageId;
    private String shareKey;


    public FindScriptsByIdResponse(Script script) {
        assert script.getId() != null;
        this.id = script.getId().getValue();
        this.name = script.getName();
        this.languageId = script.getLanguageId();
        this.shareKey = script.getShareKey();
    }
}
