package org.rbernalop.apiscript.shared.application.script.find;

import lombok.Getter;
import org.rbernalop.apiscript.script.domain.aggregate.Script;

@Getter
public class ScriptResponse {
    public final String id;
    public final String ownerId;
    private final String name;
    private final String shareKey;
    private final String languageId;

    public ScriptResponse(Script script) {
        assert script.getId() != null;
        this.id = script.getId().getValue();
        this.ownerId = script.getOwnerName();
        this.name = script.getName();
        this.shareKey = script.getShareKey();
        this.languageId = script.getLanguageId();
    }
}