package org.rbernalop.apiscript.shared.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiscript.script.domain.exception.InvalidScriptDataException;
import org.rbernalop.apiscript.shared.domain.exception.NegativeException;
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
public class ApiScriptExceptionHandler extends ResponseEntityExceptionHandler {
    private CustomError generateError(String message, HttpStatus httpStatus) {
        return CustomError.builder()
                .message(message)
                .httpCode(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidIdException.class, NegativeException.class, InvalidScriptDataException.class})
    public CustomError handleBadRequestExceptions(Exception ex) {
        return generateError(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
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
