package org.rbernalop.apiauthentication.user.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleCaptchaAPIClient", url = "https://www.google.com/recaptcha/api/siteverify")
public interface GoogleCaptchaAPIClient {
    @GetMapping
    GoogleCaptchaResponse verifyCaptcha(@RequestParam(value = "secret") String secret, @RequestParam(value = "response") String response);
}