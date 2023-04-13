package org.rbernalop.shared.domain.exception;

public class NegativeException extends RuntimeException {
    public NegativeException(String message) {
        super(message);
    }
}
