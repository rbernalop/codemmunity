package org.rbernalop.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS");

    private final String operator;

    public static FilterOperator fromValue(String value) {
        return switch (value) {
            case "=" -> FilterOperator.EQUAL;
            case "!=" -> FilterOperator.NOT_EQUAL;
            case ">" -> FilterOperator.GT;
            case "<" -> FilterOperator.LT;
            case "CONTAINS" -> FilterOperator.CONTAINS;
            case "NOT_CONTAINS" -> FilterOperator.NOT_CONTAINS;
            default -> null;
        };
    }

    public boolean isPositive() {
        return this != NOT_EQUAL && this != NOT_CONTAINS;
    }
}
