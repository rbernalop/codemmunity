package org.rbernalop.shared.application;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.bus.query.Query;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandlerExecutionError;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
public abstract class UseCase {
    private final QueryBus queryBus;

    protected <R extends Response> R ask(Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }
}
