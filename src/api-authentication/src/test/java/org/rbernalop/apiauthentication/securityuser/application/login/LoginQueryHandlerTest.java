package org.rbernalop.apiauthentication.securityuser.application.login;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiauthentication.securityuser.domain.port.TokenGenerator;
import org.rbernalop.apiauthentication.securityuser.domain.port.UserAuthenticator;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQuery;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryMother;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryResponse;
import org.rbernalop.apiauthentication.shared.application.securityuser.login.LoginQueryResponseMother;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQuery;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQueryMother;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponse;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponseMother;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginQueryHandlerTest extends UnitTestCase {

    @Mock
    private TokenGenerator tokenGenerator;



    @Mock
    private UserAuthenticator userAuthenticator;

    @InjectMocks
    private LoginQueryHandler loginQueryHandler;

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

        doNothing().when(userAuthenticator).authenticate(loginQuery.getUsername(), loginQuery.getPassword());
        when(tokenGenerator.generateToken(loginQuery.getUsername(), null, null)).thenReturn(expectedLoginResponse.getToken());
        when(queryBus.ask(findUserByUsernameQuery)).thenReturn(findUserByUsernameResponse);

        // WHEN
        LoginQueryResponse actualLoginResponse = loginQueryHandler.handle(loginQuery);

        // THEN
        assertEquals(expectedLoginResponse, actualLoginResponse);
        verify(userAuthenticator, times(1)).authenticate(loginQuery.getUsername(), loginQuery.getPassword());
        verify(tokenGenerator, times(1)).generateToken(loginQuery.getUsername(), null, null);
        verify(queryBus, times(1)).ask(findUserByUsernameQuery);
    }
}