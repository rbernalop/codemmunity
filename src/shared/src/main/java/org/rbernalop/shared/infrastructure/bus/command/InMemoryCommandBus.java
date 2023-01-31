package org.rbernalop.shared.infrastructure.bus.command;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.command.Command;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.command.CommandHandler;
import org.rbernalop.shared.domain.bus.command.CommandHandlerExecutionError;

@Service
@AllArgsConstructor
public final class InMemoryCommandBus implements CommandBus {
    private final CommandHandlersInformation information;
    private final ApplicationContext         context;

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());
            CommandHandler handler = context.getBean(commandHandlerClass);
            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}