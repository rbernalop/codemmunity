package org.rbernalop.apichallenge.shared.application.challenge.find;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCodeResponse {
    private String challengeId;
    private String languageName;
    private String content;

    public static BaseCodeResponse from(String challengeId, String languageName, String content) {
        BaseCodeResponse baseCodeResponse = new BaseCodeResponse();
        baseCodeResponse.setChallengeId(challengeId);
        baseCodeResponse.setLanguageName(languageName);
        baseCodeResponse.setContent(content);
        return baseCodeResponse;
    }
}
