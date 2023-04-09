package org.rbernalop.apiauthentication.shared.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiauthentication.securityuser.domain.exception.InvalidCredentialsException;
import org.rbernalop.apiauthentication.user.domain.exception.InvalidCaptchaException;
import org.rbernalop.shared.domain.exception.InvalidUserDataException;
import org.rbernalop.apiauthentication.user.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.user.domain.exception.UserNotFoundException;
import org.rbernalop.shared.domain.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ApiAuthenticationExceptionHandler extends ResponseEntityExceptionHandler {
    private CustomError generateError(String message, HttpStatus httpStatus) {
        return CustomError.builder()
                .message(message)
                .httpCode(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidUserDataException.class, InvalidIdException.class, InvalidCaptchaException.class})
    public CustomError handleBadRequestExceptions(Exception ex) {
        return generateError(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public CustomError handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return generateError(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidCredentialsException.class)
    public CustomError handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return generateError(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public CustomError handleUserNotFoundException(UserNotFoundException ex) {
        return generateError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

@Builder
@Getter
@Setter
class CustomError {
    private String message;
    private int httpCode;
    private LocalDateTime timestamp;
}
