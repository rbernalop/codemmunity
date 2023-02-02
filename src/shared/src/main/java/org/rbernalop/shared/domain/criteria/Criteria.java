package org.rbernalop.shared.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public final class Criteria {
    private final Filters filters;
    private final Order order;
    private final Optional<Integer> limit;
    private final Optional<Integer> offset;

    public Criteria(Filters filters, Order order) {
        this.filters = filters;
        this.order   = order;
        this.limit   = Optional.empty();
        this.offset  = Optional.empty();
    }

    public boolean hasFilters() {
        return filters.getFilters().size() > 0;
    }

    public String serialize() {
        return String.format(
                "%s~~%s~~%s~~%s",
                filters.serialize(),
                order.serialize(),
                offset.orElse(0),
                limit.orElse(0)
        );
    }
}
