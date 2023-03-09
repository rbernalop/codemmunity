package org.rbernalop.apiscript.script.domain.value_object;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apiscript.script.domain.exception.InvalidScriptDataException;

@NoArgsConstructor
@EqualsAndHashCode
public class OwnerUsername {
    private String userUsername;

    public OwnerUsername(String userUsername) {
        if(userUsername == null || userUsername.isBlank()) {
            throw new InvalidScriptDataException("User username cannot be empty");
        }
        this.userUsername = userUsername;
    }

    public String getValue() {
        return userUsername;
    }
}
