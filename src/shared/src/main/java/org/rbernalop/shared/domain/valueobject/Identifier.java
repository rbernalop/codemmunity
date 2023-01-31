package org.rbernalop.shared.domain.valueobject;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
public abstract class Identifier implements Serializable {
    protected String value;

    public Identifier(String value) {
        ensureValidUuid(value);

        this.value = value;
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }
}

