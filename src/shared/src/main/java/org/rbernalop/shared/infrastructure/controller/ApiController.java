package org.rbernalop.shared.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.rbernalop.shared.domain.bus.command.Command;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.command.CommandHandlerExecutionError;
import org.rbernalop.shared.domain.bus.query.Query;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandlerExecutionError;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
public abstract class ApiController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    protected void dispatch(Command command) throws CommandHandlerExecutionError {
        commandBus.dispatch(command);
    }

    protected <R extends Response> R ask(Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }
}
