package org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids;

import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCodeMother;

import java.util.List;

public class FindBaseCodeByChallengeIdsResponsesMother {
    public static FindBaseCodeByChallengeIdsResponses random() {
        BaseCode baseCode = BaseCodeMother.random();

        return FindBaseCodeByChallengeIdsResponses.fromBaseCodes(List.of(baseCode));
    }
}