package org.rbernalop.shared.infrastructure.jpa;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.criteria.Criteria;
import org.rbernalop.shared.domain.criteria.Filter;
import org.rbernalop.shared.domain.criteria.FilterOperator;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

@AllArgsConstructor
public final class HibernateCriteriaConverter<T> {
    private CriteriaBuilder builder;

    private final HashMap<FilterOperator, BiFunction<Path<String>, String, Predicate>> predicateTransformers =
        new HashMap<>() {{
            put(FilterOperator.EQUAL, builder::equal);
            put(FilterOperator.NOT_EQUAL, builder::notEqual);
            put(FilterOperator.GT, builder::greaterThan);
            put(FilterOperator.LT, builder::lessThan);
            put(FilterOperator.CONTAINS, HibernateCriteriaConverter.this::containsPredicateTransformer);
            put(FilterOperator.NOT_CONTAINS, HibernateCriteriaConverter.this::notContainsPredicateTransformer);
        }};

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        Root<T> root = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(formatPredicates(criteria.getFilters().getFilters(), root));

        if (criteria.getOrder().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.getOrder().getOrderBy().getValue());
            Order order = criteria.getOrder().getOrderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);
            hibernateCriteria.orderBy(order);
        }

        return hibernateCriteria;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(
                filter,
                root
        )).toList();
        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);
        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Path<String>, String, Predicate> transformer = predicateTransformers.get(filter.getOperator());
        return transformer.apply(root.get(filter.getField().getValue()), filter.getValue().getValue());
    }

    private Predicate containsPredicateTransformer(Path<String> filter, String root) {
        return builder.like(filter, String.format("%%%s%%", filter));
    }

    private Predicate notContainsPredicateTransformer(Path<String> filter, String root) {
        return builder.notLike(filter, String.format("%%%s%%", filter));
    }

}
