package org.rbernalop.apiauthentication.user.application.finder.username;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.rbernalop.apiauthentication.user.application.create.UserMother;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.value_object.UserUsername;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FindUserByUsernameQueryHandlerTest extends UnitTestCase {
    @Mock
    private UserRepository userRepository;
    @Mock
    private QueryBus queryBus;

    private FindUserByUsernameQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        handler = new FindUserByUsernameQueryHandler(userRepository, queryBus);
    }

    @Test
    void should_find_user_by_username() {
        // GIVEN
        User user = UserMother.random();
        String username = user.getUsername();
        FindUserByUsernameQuery query = FindUserByUsernameQueryMother.fromUsername(username);
        when(userRepository.findByUsername(new UserUsername(username))).thenReturn(Optional.of(user));

        // WHEN
        FindUserByUsernameResponse response = handler.handle(query);

        // THEN
        assert user.getId() != null;
        assertEquals(user.getId().getId(), response.getId());
        assertEquals(user.getPassword(), response.getPassword());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getUsername(), response.getUsername());
    }
}