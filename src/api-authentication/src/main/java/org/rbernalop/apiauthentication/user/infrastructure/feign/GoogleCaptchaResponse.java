package org.rbernalop.apiauthentication.user.infrastructure.feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleCaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostname;
}

