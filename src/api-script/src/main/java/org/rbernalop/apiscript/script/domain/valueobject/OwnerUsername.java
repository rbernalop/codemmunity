package org.rbernalop.apiscript.script.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class OwnerUsername {
    private String userUsername;

    public OwnerUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getValue() {
        return userUsername;
    }
}
