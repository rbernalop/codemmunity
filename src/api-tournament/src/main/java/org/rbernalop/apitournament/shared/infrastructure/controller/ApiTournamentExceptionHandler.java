package org.rbernalop.apitournament.shared.infrastructure.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apitournament.tournament.domain.exception.InvalidTournamentDataException;
import org.rbernalop.shared.domain.InvalidIdException;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ApiTournamentExceptionHandler extends ResponseEntityExceptionHandler {
    private CustomError generateError(String message, HttpStatus httpStatus) {
        return CustomError.builder()
                .message(message)
                .httpCode(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({InvalidTournamentDataException.class, InvalidIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError handleBadRequestExceptions(Exception exception) {
        return generateError(exception.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError handleNotFoundExceptions(EntityNotFoundException exception) {
        return generateError(exception.getMessage(), HttpStatus.NOT_FOUND);
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
