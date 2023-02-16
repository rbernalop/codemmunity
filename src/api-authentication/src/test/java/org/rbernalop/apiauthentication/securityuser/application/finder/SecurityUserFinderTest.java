package org.rbernalop.apiauthentication.securityuser.application.finder;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiauthentication.user.domain.aggregate.UserMother;
import org.rbernalop.apiauthentication.shared.application.userfinder.username.FindUserByUsernameQuery;
import org.rbernalop.apiauthentication.user.application.finder.username.FindUserByUsernameQueryMother;
import org.rbernalop.apiauthentication.shared.application.userfinder.username.FindUserByUsernameResponse;
import org.rbernalop.apiauthentication.user.application.finder.username.FindUserByUsernameResponseMother;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityUserFinderTest extends UnitTestCase {
    @Mock
    private QueryBus queryBus;

    @InjectMocks
    private SecurityUserFinder securityUserFinder;

    @Test
    void should_find_user_and_return_credentials() {
        User user = UserMother.random();
        FindUserByUsernameQuery query = FindUserByUsernameQueryMother.fromUsername(user.getUsername());
        FindUserByUsernameResponse response = FindUserByUsernameResponseMother.fromUser(user);
        when(queryBus.ask(query)).thenReturn(response);

        UserDetails actualUser = securityUserFinder.loadUserByUsername(query.getUsername());


        assertEquals(response.getUsername(), actualUser.getUsername());
        assertEquals(response.getPassword(), actualUser.getPassword());
        verify(queryBus, times(1)).ask(query);
    }
}