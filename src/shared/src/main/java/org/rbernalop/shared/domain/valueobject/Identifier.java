package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.InvalidIdException;

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

    private void ensureValidUuid(String value) throws InvalidIdException {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException("Invalid id format '" + value + "'");
        }
    }

    public String getId() {
        return id;
    }
}

