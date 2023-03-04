package org.rbernalop.apiscript.script.domain.valueobject;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ShareKey {
    private String key;

    public ShareKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return key;
    }
}
