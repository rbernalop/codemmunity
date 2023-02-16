package org.rbernalop.apiauthentication.user.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiauthentication.shared.application.user.create.CreateUserCommand;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.command.CommandHandlerExecutionError;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1")
public final class UserPostController extends ApiController {
    public UserPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserPostRequest request) throws CommandHandlerExecutionError {
        CreateUserCommand command = request.toCommand();
        dispatch(command);
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