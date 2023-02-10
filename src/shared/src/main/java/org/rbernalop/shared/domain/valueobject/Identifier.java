package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@MappedSuperclass
public abstract class Identifier implements Serializable {
    protected String id;

    public Identifier(String id) {
        ensureValidUuid(id);

        this.id = id;
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }

    public String getId() {
        return id;
    }
}

