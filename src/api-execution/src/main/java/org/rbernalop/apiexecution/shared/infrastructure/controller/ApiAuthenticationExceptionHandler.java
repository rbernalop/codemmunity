package org.rbernalop.apiexecution.shared.infrastructure.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiexecution.language.domain.exception.InvalidLanguageDataException;
import org.rbernalop.apiexecution.script.domain.exception.CompilationException;
import org.rbernalop.apiexecution.script.domain.exception.ExecutionException;
import org.rbernalop.apiexecution.script.domain.exception.FactoryException;
import org.rbernalop.apiexecution.script.domain.exception.FileException;
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
public class ApiAuthenticationExceptionHandler extends ResponseEntityExceptionHandler {
    private CustomError generateError(String message, HttpStatus httpStatus) {
        return CustomError.builder()
                .message(message)
                .httpCode(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    private CodeError generateCodeError(String compilationError, String executionError, HttpStatus httpStatus) {
        return CodeError.builder()
                .compilationError(compilationError)
                .executionError(executionError)
                .httpCode(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompilationException.class)
    public CodeError handleCompilationException(CompilationException ex) {
        return generateCodeError(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExecutionException.class)
    public CodeError handleExecutionException(ExecutionException ex) {
        return generateCodeError(null, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({FactoryException.class, FileException.class, InvalidLanguageDataException.class, InvalidIdException.class})
    public CustomError handleBadRequestExceptions(Exception ex) {
        return generateError(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public CustomError handleNotFoundExceptions(EntityNotFoundException ex) {
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

@Builder
@Getter
@Setter
class CodeError {
    private String compilationError;
    private String executionError;
    private int httpCode;
    private LocalDateTime timestamp;
}
