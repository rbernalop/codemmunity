package org.rbernalop.shared.domain;

import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public DomainException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode    = errorCode;
        this.errorMessage = errorMessage;
    }
}
