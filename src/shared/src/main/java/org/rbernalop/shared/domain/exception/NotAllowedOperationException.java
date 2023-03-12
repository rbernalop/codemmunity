package org.rbernalop.shared.domain.exception;

public class NotAllowedOperationException extends RuntimeException {
    public NotAllowedOperationException(String message) {
        super(message);
    }
}
