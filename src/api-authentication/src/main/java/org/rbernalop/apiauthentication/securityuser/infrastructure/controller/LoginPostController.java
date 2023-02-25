package org.rbernalop.apiauthentication.securityuser.infrastructure.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQuery;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LoginPostController extends ApiController {
    public LoginPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("login")
    public LoginResponse handle(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginQueryResponse loginQueryResponse = ask(
            new LoginQuery(loginRequest.getUsername(), loginRequest.getPassword())
        );

        response.addHeader("Authorization", "Bearer " + loginQueryResponse.getToken());

        return new LoginResponse(
                loginQueryResponse.getId(),
                loginQueryResponse.getUsername(),
                loginQueryResponse.getEmail()
        );
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class LoginRequest {
    private String username;
    private String password;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
class LoginResponse {
    private String id;
    private String username;
    private String email;
}