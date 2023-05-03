package org.rbernalop.servicechallengesynchronization.shared.application.script.find.by_id;

import lombok.*;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindScriptByChallengeIdResponse implements Response {
    private String content;
    private String languageName;

    public static FindScriptByChallengeIdResponse from(ChallengeScript challengeScript) {
        return new FindScriptByChallengeIdResponse(challengeScript.getScriptContent(), challengeScript.getLanguageName());
    }
}
