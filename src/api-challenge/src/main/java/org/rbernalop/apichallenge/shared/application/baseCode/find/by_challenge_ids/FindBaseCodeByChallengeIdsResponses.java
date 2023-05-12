package org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FindBaseCodeByChallengeIdsResponses implements Response {
    private List<FindBaseCodeByChallengeIdsResponse> baseCodes;

    public static FindBaseCodeByChallengeIdsResponses fromBaseCodes(List<BaseCode> baseCodes) {
        return new FindBaseCodeByChallengeIdsResponses(
            baseCodes.stream().map(FindBaseCodeByChallengeIdsResponse::fromBaseCode).toList()
        );
    }
}
