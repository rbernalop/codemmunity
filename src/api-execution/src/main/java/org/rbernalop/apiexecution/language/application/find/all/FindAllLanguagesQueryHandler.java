package org.rbernalop.apiexecution.language.application.find.all;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiexecution.language.application.find.LanguageFinder;
import org.rbernalop.apiexecution.language.domain.port.LanguageRepository;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesQuery;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesResponse;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindAllLanguagesQueryHandler implements QueryHandler<FindAllLanguagesQuery, FindAllLanguagesResponse> {

    private final LanguageFinder languageFinder;

    public FindAllLanguagesQueryHandler(QueryBus queryBus, LanguageRepository languageRepository) {
        this.languageFinder = new LanguageFinder(queryBus, languageRepository);
    }

    @Override
    public FindAllLanguagesResponse handle(FindAllLanguagesQuery query) {
        log.info("Finding all languages");
        return languageFinder.findAll();
    }
}
