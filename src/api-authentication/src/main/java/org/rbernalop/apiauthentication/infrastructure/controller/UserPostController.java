package org.rbernalop.apiauthentication.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiauthentication.application.create.CreateUserCommand;
import org.rbernalop.shared.domain.DomainException;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.command.CommandHandlerExecutionError;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public final class UserPostController extends ApiController {
    public UserPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public void handle(@RequestBody UserPostRequest request) throws CommandHandlerExecutionError {
        CreateUserCommand command = request.toCommand();
        dispatch(command);
    }

    @Override
    public HashMap<Class<? extends DomainException>, HttpStatus> errorMapping() {
        return null;
    }
}

@Getter
@Setter
class UserPostRequest {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private LocalDate birthDate;

    CreateUserCommand toCommand() {
        return new CreateUserCommand(
                id,
                name,
                surname,
                username,
                email,
                password,
                birthDate
        );
    }
}