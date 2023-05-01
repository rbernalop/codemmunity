package org.rbernalop.apichallenge.shared.application.baseCode.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.shared.domain.bus.query.Response;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindBaseCodeByIdResponse implements Response {
    private String challengeId;
    private String languageName;
    private String code;

    public static FindBaseCodeByIdResponse fromBaseCode(BaseCode baseCode) {
        return new FindBaseCodeByIdResponse(baseCode.getChallengeId(), baseCode.getLanguageName(), baseCode.getContent());
    }
}
