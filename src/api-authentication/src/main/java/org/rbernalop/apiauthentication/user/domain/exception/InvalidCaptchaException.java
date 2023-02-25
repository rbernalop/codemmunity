package org.rbernalop.apiauthentication.user.domain.exception;

public class InvalidCaptchaException extends RuntimeException {
    public InvalidCaptchaException(String message) {
        super(message);
    }
}
