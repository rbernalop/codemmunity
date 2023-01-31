package org.rbernalop.shared.domain.valueobject;

import lombok.Getter;

@Getter
public abstract class StringValueObject {
    private final String value;

    public StringValueObject(String value) {
        this.value = value;
    }
}