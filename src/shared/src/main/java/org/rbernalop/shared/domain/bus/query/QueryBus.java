package org.rbernalop.shared.domain.bus.query;

public interface QueryBus {
    <R extends Response> R ask(Query query) throws org.rbernalop.shared.domain.bus.query.QueryHandlerExecutionError;
}
