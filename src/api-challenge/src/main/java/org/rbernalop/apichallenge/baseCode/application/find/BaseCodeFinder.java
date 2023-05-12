package org.rbernalop.apichallenge.baseCode.application.find;

import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.port.BaseCodeRepository;
import org.rbernalop.apichallenge.baseCode.domain.value_object.BaseCodeId;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_challenge_ids.FindBaseCodeByChallengeIdsResponses;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_id.FindBaseCodeByIdResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.ChallengeId;

import java.util.List;
import java.util.Optional;

public class BaseCodeFinder extends UseCase {
    private final BaseCodeRepository baseCodeRepository;

    public BaseCodeFinder(QueryBus queryBus, EventBus eventBus, BaseCodeRepository baseCodeRepository) {
        super(queryBus, eventBus);
        this.baseCodeRepository = baseCodeRepository;
    }


    public FindBaseCodeByIdResponse findById(BaseCodeId baseCodeId) {
        Optional<BaseCode> baseCode = baseCodeRepository.findById(baseCodeId);

        if (baseCode.isEmpty()) {
            throw new EntityNotFoundException("Base code for challenge " + baseCodeId.getChallengeId() + " and language " + baseCodeId.getLanguageName() + " not found");
        }

        return FindBaseCodeByIdResponse.fromBaseCode(baseCode.get());
    }

    public FindBaseCodeByChallengeIdsResponses findByChallengeIds(List<ChallengeId> challengeIds) {
        List<BaseCode> baseCodes = baseCodeRepository.findById_ChallengeIdIn(challengeIds);

        return FindBaseCodeByChallengeIdsResponses.fromBaseCodes(baseCodes);
    }
}
