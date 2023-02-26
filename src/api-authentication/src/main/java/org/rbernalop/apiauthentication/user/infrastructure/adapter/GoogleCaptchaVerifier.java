package org.rbernalop.apiauthentication.user.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.rbernalop.apiauthentication.user.domain.port.CaptchaVerifier;
import org.rbernalop.apiauthentication.user.infrastructure.configuration.GoogleCaptchaConfiguration;
import org.rbernalop.apiauthentication.user.infrastructure.feign.GoogleCaptchaAPIClient;
import org.rbernalop.apiauthentication.user.infrastructure.feign.GoogleCaptchaResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GoogleCaptchaVerifier implements CaptchaVerifier {
    private final GoogleCaptchaAPIClient googleCaptchaAPIClient;

    private final GoogleCaptchaConfiguration googleCaptchaConfiguration;

    @Override
    public boolean verifyCaptcha(String captchaToken) {
        GoogleCaptchaResponse response =
            googleCaptchaAPIClient.verifyCaptcha(googleCaptchaConfiguration.getSecret(), captchaToken);
        return response.isSuccess();
    }
}
