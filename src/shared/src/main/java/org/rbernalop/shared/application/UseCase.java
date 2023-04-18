package org.rbernalop.shared.application;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.Query;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandlerExecutionError;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
public abstract class UseCase {
    protected final QueryBus queryBus;
    protected final EventBus eventBus;

    protected <R extends Response> R ask(Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }
}
