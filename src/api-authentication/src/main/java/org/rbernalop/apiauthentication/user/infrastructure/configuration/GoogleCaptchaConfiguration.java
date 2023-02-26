package org.rbernalop.apiauthentication.user.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.google")
public class GoogleCaptchaConfiguration {
    private String secret;
}
