package org.rbernalop.apiexecution.script.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ScriptContent {
    private String content;

    public ScriptContent(String content) {
        this.content = content;
    }

    public String getValue() {
        return content;
    }
}
