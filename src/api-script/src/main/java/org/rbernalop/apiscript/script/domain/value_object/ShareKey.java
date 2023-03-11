package org.rbernalop.apiscript.script.domain.value_object;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ShareKey extends Identifier {
    private String key;

    public ShareKey(String key) {
        super(key);
        this.key = key;
    }

    public String getValue() {
        return key;
    }
}
