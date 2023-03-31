package org.rbernalop.apiexecution.language.application.find.by_id;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiexecution.language.application.find.LanguageFinder;
import org.rbernalop.apiexecution.language.domain.repository.LanguageRepository;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdQuery;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;
import org.rbernalop.shared.domain.valueobject.LanguageId;

@Service
@Slf4j
public class FindLanguageByIdQueryHandler implements QueryHandler<FindLanguageByIdQuery, FindLanguageByIdResponse> {

    private final LanguageFinder languageFinder;

    public FindLanguageByIdQueryHandler(QueryBus queryBus, LanguageRepository languageRepository) {
        this.languageFinder = new LanguageFinder(queryBus, languageRepository);
    }

    @Override
    public FindLanguageByIdResponse handle(FindLanguageByIdQuery query) {
        log.info("Finding language by id: {}", query.getId());
        LanguageId languageId = new LanguageId(query.getId());
        return languageFinder.findById(languageId);
    }
}
