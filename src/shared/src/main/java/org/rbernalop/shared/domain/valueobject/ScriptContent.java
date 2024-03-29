package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ScriptContent {
    @Column(columnDefinition = "TEXT")
    private String content;

    public ScriptContent(String content) {
        this.content = content;
    }

    public String getValue() {
        return content;
    }
}
