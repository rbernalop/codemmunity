package org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindBaseCodeByChallengeIdsResponse {
    private String challengeId;
    private String languageName;
    private String code;

    public static FindBaseCodeByChallengeIdsResponse fromBaseCode(BaseCode baseCode) {
        return new FindBaseCodeByChallengeIdsResponse(baseCode.getChallengeId(), baseCode.getLanguageName(), baseCode.getContent());
    }
}
