package org.rbernalop.shared.domain.valueobject;

import lombok.Getter;

@Getter
public abstract class IntValueObject {
    private final Integer value;

    public IntValueObject(Integer value) {
        this.value = value;
    }
}
