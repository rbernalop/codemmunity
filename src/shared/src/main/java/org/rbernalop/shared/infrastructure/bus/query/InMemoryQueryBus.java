package org.rbernalop.shared.infrastructure.bus.query;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.*;

@Service
@AllArgsConstructor
public final class InMemoryQueryBus implements QueryBus {
    private final QueryHandlersInformation information;
    private final ApplicationContext context;

    @Override
    public Response ask(Query query) throws QueryHandlerExecutionError {
        try {
            Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());
            QueryHandler handler = context.getBean(queryHandlerClass);
            return handler.handle(query);
        } catch (Throwable error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}