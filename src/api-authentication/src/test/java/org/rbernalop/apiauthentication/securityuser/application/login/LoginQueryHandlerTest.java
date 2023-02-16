package org.rbernalop.apiauthentication.securityuser.application.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.rbernalop.apiauthentication.securityuser.domain.port.TokenGenerator;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQuery;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryMother;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryResponse;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryResponseMother;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQuery;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQueryMother;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponse;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponseMother;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginQueryHandlerTest extends UnitTestCase {

    @Mock
    private TokenGenerator tokenGenerator;

    @Mock
    private QueryBus queryBus;

    private LoginQueryHandler loginQueryHandler;

    @BeforeEach
    void setUp() {
        loginQueryHandler = new LoginQueryHandler(tokenGenerator, queryBus);
    }

    @Test
    void should_make_login_and_return_token() {
        // GIVEN
        LoginQuery loginQuery = LoginQueryMother.random();

        FindUserByUsernameQuery findUserByUsernameQuery = FindUserByUsernameQueryMother.fromUsername(loginQuery.getUsername());

        FindUserByUsernameResponse findUserByUsernameResponse = FindUserByUsernameResponseMother.fromCredentials(
            loginQuery.getUsername(),
            loginQuery.getPassword());

        LoginQueryResponse expectedLoginResponse = LoginQueryResponseMother.fromUserData(
            findUserByUsernameResponse.getId(),
            findUserByUsernameResponse.getUsername(),
            findUserByUsernameResponse.getEmail());

        when(tokenGenerator.generateToken(loginQuery.getUsername(), loginQuery.getPassword())).thenReturn(expectedLoginResponse.getToken());
        when(queryBus.ask(findUserByUsernameQuery)).thenReturn(findUserByUsernameResponse);

        // WHEN
        LoginQueryResponse actualLoginResponse = loginQueryHandler.handle(loginQuery);

        // THEN
        assertEquals(expectedLoginResponse, actualLoginResponse);
        verify(tokenGenerator, times(1)).generateToken(loginQuery.getUsername(), loginQuery.getPassword());
        verify(queryBus, times(1)).ask(findUserByUsernameQuery);
    }
}