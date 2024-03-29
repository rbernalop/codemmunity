package org.rbernalop.apiauthentication.securityuser.application.login;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiauthentication.securityuser.domain.port.TokenGenerator;
import org.rbernalop.apiauthentication.securityuser.domain.port.UserAuthenticator;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQuery;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class LoginQueryHandler implements QueryHandler<LoginQuery, LoginQueryResponse> {

    private final UserLoginUseCase userLoginUseCase;

    public LoginQueryHandler(QueryBus queryBus,
            EventBus eventBus,
            TokenGenerator tokenGenerator,
            UserAuthenticator userAuthenticator) {
        userLoginUseCase = new UserLoginUseCase(queryBus, eventBus, tokenGenerator, userAuthenticator);
    }

    @Override
    public LoginQueryResponse handle(LoginQuery loginQuery) {
        log.info("Logging in user with username: {}", loginQuery.getUsername());
        UserLoginResponse userLoginResponse = userLoginUseCase.login(loginQuery.getUsername(), loginQuery.getPassword());
        return new LoginQueryResponse(
            userLoginResponse.getToken(),
            userLoginResponse.getId(),
            userLoginResponse.getUsername(),
            userLoginResponse.getEmail()
        );
    }
}
