package org.rbernalop.shared.domain.valueobject;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
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
