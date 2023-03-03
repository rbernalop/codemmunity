package org.rbernalop.apiauthentication.user.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.rbernalop.apiauthentication.shared.application.user.create.CreateUserCommand;
import org.rbernalop.apiauthentication.shared.application.user.create.CreateUserCommandMother;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.aggregate.UserMother;
import org.rbernalop.apiauthentication.user.domain.exception.InvalidCaptchaException;
import org.rbernalop.apiauthentication.user.domain.exception.InvalidUserDataException;
import org.rbernalop.apiauthentication.user.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.user.domain.port.CaptchaVerifier;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.value_object.UserEmail;
import org.rbernalop.apiauthentication.user.domain.value_object.UserId;
import org.rbernalop.apiauthentication.user.domain.value_object.UserUsername;
import org.rbernalop.shared.domain.InvalidIdException;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateUserCommandHandlerTest extends UnitTestCase {

    @Mock
    private UserRepository repository;

    @Mock
    private QueryBus queryBus;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CaptchaVerifier captchaVerifier;

    private CreateUserCommandHandler handler;

    @BeforeEach
    void setUp() {
        handler = new CreateUserCommandHandler(repository, queryBus, passwordEncoder, captchaVerifier);
    }

    @Test
    void should_create_a_valid_user() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.random();
        User user = UserMother.fromCommand(command);
        UserId userId = user.getId();
        UserUsername userUsername = new UserUsername(user.getUsername());
        UserEmail userEmail = new UserEmail(user.getEmail());

        assert userId != null;
        when(repository.findById(userId)).thenReturn(Optional.empty());
        when(repository.findByUsername(userUsername)).thenReturn(Optional.empty());
        when(repository.findByEmail(userEmail)).thenReturn(Optional.empty());
        when(captchaVerifier.verifyCaptcha(command.getCaptchaToken())).thenReturn(true);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(repository.save(user)).thenReturn(user);

        // WHEN
        handler.handle(command);

        // THEN
        verify(repository, times(1)).findById(userId);
        verify(repository, times(1)).findByUsername(userUsername);
        verify(repository, times(1)).findByEmail(userEmail);
        verify(captchaVerifier, times(1)).verifyCaptcha(command.getCaptchaToken());
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(repository, times(1)).save(user);
    }

    @Test
    void should_throw_UserAlreadyExistsException_when_id_already_exists() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.random();
        User user = UserMother.fromCommand(command);
        UserId userId = user.getId();

        assert userId != null;
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        // WHEN
        UserAlreadyExistsException actualException =
            assertThrows(UserAlreadyExistsException.class, () -> handler.handle(command));

        // THEN
        assertEquals("User with id " + userId.getValue() + " already exists", actualException.getMessage());
        verify(repository, times(1)).findById(userId);
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_UserAlreadyExistsException_when_username_already_exists() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.random();
        User user = UserMother.fromCommand(command);
        UserId userId = user.getId();
        UserUsername userUsername = new UserUsername(user.getUsername());

        assert userId != null;
        when(repository.findById(userId)).thenReturn(Optional.empty());
        when(repository.findByUsername(userUsername)).thenReturn(Optional.of(user));

        // WHEN
        UserAlreadyExistsException actualException =
            assertThrows(UserAlreadyExistsException.class, () -> handler.handle(command));

        // THEN
        assertEquals("User already exists", actualException.getMessage());
        verify(repository, times(1)).findById(userId);
        verify(repository, times(1)).findByUsername(userUsername);
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_UserAlreadyExistsException_when_email_already_exists() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.random();
        User user = UserMother.fromCommand(command);
        UserId userId = user.getId();
        UserUsername userUsername = new UserUsername(user.getUsername());
        UserEmail userEmail = new UserEmail(user.getEmail());

        assert userId != null;
        when(repository.findById(userId)).thenReturn(Optional.empty());
        when(repository.findByUsername(userUsername)).thenReturn(Optional.empty());
        when(repository.findByEmail(userEmail)).thenReturn(Optional.of(user));

        // WHEN
        UserAlreadyExistsException actualException =
            assertThrows(UserAlreadyExistsException.class, () -> handler.handle(command));

        // THEN
        assertEquals("Email already exists", actualException.getMessage());
        verify(repository, times(1)).findById(userId);
        verify(repository, times(1)).findByUsername(userUsername);
        verify(repository, times(1)).findByEmail(userEmail);
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_InvalidUserDataException_when_birthdate_is_under_18() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.randomUnderAge();

        // WHEN
        InvalidUserDataException actualException =
            assertThrows(InvalidUserDataException.class, () -> handler.handle(command));

        // THEN
        assertEquals("User must be over 18 years old", actualException.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_InvalidUserDataException_when_email_is_invalid() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.randomWithInvalidEmail();

        // WHEN
        InvalidUserDataException actualException =
            assertThrows(InvalidUserDataException.class, () -> handler.handle(command));

        // THEN
        assertEquals("Invalid email", actualException.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_InvalidUserDataException_when_name_is_blank() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.randomWithBlankName();

        // WHEN
        InvalidUserDataException actualException =
            assertThrows(InvalidUserDataException.class, () -> handler.handle(command));

        // THEN
        assertEquals("User name cannot be empty", actualException.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_InvalidUserDataException_when_surname_is_blank() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.randomWithBlankSurname();

        // WHEN
        InvalidUserDataException actualException =
            assertThrows(InvalidUserDataException.class, () -> handler.handle(command));

        // THEN
        assertEquals("User name cannot be empty", actualException.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_InvalidUserDataException_when_username_is_blank() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.randomWithBlankUsername();

        // WHEN
        InvalidUserDataException actualException =
            assertThrows(InvalidUserDataException.class, () -> handler.handle(command));

        // THEN
        assertEquals("User username cannot be empty", actualException.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
    void should_throw_InvalidIdException_when_id_is_invalid() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.randomWithInvalidId();

        // WHEN
        InvalidIdException actualException =
            assertThrows(InvalidIdException.class, () -> handler.handle(command));

        // THEN
        assertEquals("Invalid id format '" + command.getId() + "'", actualException.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).findByUsername(any());
        verify(repository, never()).findByEmail(any());
        verify(captchaVerifier, never()).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }

    @Test
        void should_throw_InvalidCaptchaException_when_captcha_is_invalid() {
        // GIVEN
        CreateUserCommand command = CreateUserCommandMother.random();
        when(repository.findById(any())).thenReturn(Optional.empty());
        when(repository.findByUsername(any())).thenReturn(Optional.empty());
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        when(captchaVerifier.verifyCaptcha(any())).thenThrow(new InvalidCaptchaException("Captcha verification failed"));

        // WHEN
        InvalidCaptchaException actualException =
                assertThrows(InvalidCaptchaException.class, () -> handler.handle(command));

        // THEN
        assertEquals("Captcha verification failed", actualException.getMessage());
        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).findByUsername(any());
        verify(repository, times(1)).findByEmail(any());
        verify(captchaVerifier, times(1)).verifyCaptcha(any());
        verify(passwordEncoder, never()).encode(any());
        verify(repository, never()).save(any());
    }
}
