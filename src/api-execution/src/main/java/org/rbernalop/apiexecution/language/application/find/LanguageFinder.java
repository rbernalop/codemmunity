package org.rbernalop.apiexecution.language.application.find;

import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.language.domain.repository.LanguageRepository;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesResponse;
import org.rbernalop.shared.domain.bus.query.QueryBus;

import java.util.List;

public class LanguageFinder {
    private final QueryBus queryBus;

    private final LanguageRepository languageRepository;

    public LanguageFinder(QueryBus queryBus, LanguageRepository languageRepository) {
        this.queryBus = queryBus;
        this.languageRepository = languageRepository;
    }

    public FindAllLanguagesResponse findAll() {
        List<Language> languages = languageRepository.findAll();
        return new FindAllLanguagesResponse(languages);
    }
}
