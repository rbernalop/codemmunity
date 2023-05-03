package org.rbernalop.apichallenge.shared.application.baseCode.find;

import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;

public class FindBaseCodeByIdQueryMother {
    public static FindBaseCodeByIdQuery fromBaseCode(BaseCode baseCode) {
        return new FindBaseCodeByIdQuery(
                baseCode.getBaseCodeId().getChallengeId(),
                baseCode.getBaseCodeId().getLanguageName()
        );
    }
}