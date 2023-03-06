package org.rbernalop.shared.domain.valueobject;

import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;
import org.rbernalop.shared.domain.InvalidIdException;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@MappedSuperclass
public abstract class Identifier implements Serializable {

    public Identifier(String id) {
        ensureValidUuid(id);
    }

    private void ensureValidUuid(String value) throws InvalidIdException {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException("Invalid id format '" + value + "'");
        }
    }

    public abstract String getValue();
}

