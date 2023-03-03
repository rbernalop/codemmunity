package org.rbernalop.apiscript.script.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class UserId {
    private String userId;

    public UserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
