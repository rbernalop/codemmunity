package org.rbernalop.apichallenge.shared.application.completion.find.by_ids;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompletionResponse {
    private String scriptContent;
    private String languageName;

    public static CompletionResponse from(String scriptContent, String languageName) {
        CompletionResponse completionResponse = new CompletionResponse();
        completionResponse.setScriptContent(scriptContent);
        completionResponse.setLanguageName(languageName);
        return completionResponse;
    }
}
