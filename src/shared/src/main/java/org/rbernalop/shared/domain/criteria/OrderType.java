package org.rbernalop.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
    ASC("asc"),
    DESC("desc"),
    NONE("none");
    private final String type;

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isAsc() {
        return this == ASC;
    }
}
