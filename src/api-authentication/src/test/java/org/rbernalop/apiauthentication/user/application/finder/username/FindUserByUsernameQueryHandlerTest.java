package org.rbernalop.apiauthentication.user.application.finder.username;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQuery;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQueryMother;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponse;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponseMother;
import org.rbernalop.apiauthentication.user.domain.aggregate.UserMother;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FindUserByUsernameQueryHandlerTest extends UnitTestCase {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserByUsernameQueryHandler handler;

    @Test
    void should_find_user_by_username() {
        // GIVEN
        User user = UserMother.random();
        String username = user.getUsername();
        FindUserByUsernameResponse expectedResponse = FindUserByUsernameResponseMother.fromUser(user);
        FindUserByUsernameQuery query = FindUserByUsernameQueryMother.fromUsername(username);
        when(userRepository.findByUsername(new UserUsername(username))).thenReturn(Optional.of(user));

        // WHEN
        FindUserByUsernameResponse response = handler.handle(query);

        // THEN
        assertEquals(expectedResponse, response);
    }
}