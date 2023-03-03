package org.rbernalop.apiscript.script.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.valueobject.Identifier;

@NoArgsConstructor
@EqualsAndHashCode
public class UserId extends Identifier {
    private String userId;

    public UserId(String userId) {
        super(userId);
        this.userId = userId;
    }

    @Override
    public String getValue() {
        return userId;
    }
}
