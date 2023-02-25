package org.rbernalop.apiauthentication.user.domain.port;

public interface CaptchaVerifier {
    boolean verifyCaptcha(String captchaToken);
}
