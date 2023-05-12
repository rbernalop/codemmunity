package org.rbernalop.apichallenge.baseCode.application.find.by_id;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apichallenge.baseCode.application.find.BaseCodeFinder;
import org.rbernalop.apichallenge.baseCode.domain.port.BaseCodeRepository;
import org.rbernalop.apichallenge.baseCode.domain.value_object.BaseCodeId;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_id.FindBaseCodeByIdQuery;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_id.FindBaseCodeByIdResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;

@Service
@Slf4j
public class FindBaseCodeByIdQueryHandler implements QueryHandler<FindBaseCodeByIdQuery, FindBaseCodeByIdResponse> {
    private final BaseCodeFinder baseCodeFinder;

    public FindBaseCodeByIdQueryHandler(QueryBus queryBus, EventBus eventBus, BaseCodeRepository baseCodeRepository) {
        this.baseCodeFinder = new BaseCodeFinder(queryBus, eventBus, baseCodeRepository);
    }

    @Override
    public FindBaseCodeByIdResponse handle(FindBaseCodeByIdQuery query) {
        log.info("Finding base code by id: {}", query);

        ChallengeId challengeId = new ChallengeId(query.getChallengeId());
        LanguageName languageName = new LanguageName(query.getLanguageName());
        BaseCodeId baseCodeId = new BaseCodeId(challengeId, languageName);
        return baseCodeFinder.findById(baseCodeId);
    }
}
