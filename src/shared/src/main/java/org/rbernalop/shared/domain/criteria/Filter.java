package org.rbernalop.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public final class Filter {
    private final FilterField    field;
    private final FilterOperator operator;
    private final FilterValue    value;

    public static Filter create(String field, String operator, String value) {
        return new Filter(
                new FilterField(field),
                FilterOperator.fromValue(operator.toUpperCase()),
                new FilterValue(value)
        );
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return new Filter(
                new FilterField(values.get("field")),
                FilterOperator.fromValue(values.get("operator")),
                new FilterValue(values.get("value"))
        );
    }


    public String serialize() {
        return String.format("%s.%s.%s", field.getValue(), operator.getOperator(), value.getValue());
    }
}
