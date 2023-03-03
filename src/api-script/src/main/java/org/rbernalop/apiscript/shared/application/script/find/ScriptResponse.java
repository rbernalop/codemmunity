package org.rbernalop.apiscript.shared.application.script.find;

import lombok.Getter;
import org.rbernalop.apiscript.script.domain.aggregate.Script;

@Getter
public class ScriptResponse {
    public String id;
    public String ownerId;
    private String name;
    private String shareKey;
    private String languageId;

    public ScriptResponse(Script script) {
        this.id = script.getId();
        this.ownerId = script.getOwnerId();
        this.name = script.getName();
        this.shareKey = script.getShareKey();
        this.languageId = script.getLanguageId();
    }
}