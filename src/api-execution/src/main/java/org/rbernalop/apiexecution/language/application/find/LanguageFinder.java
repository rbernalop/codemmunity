package org.rbernalop.apiexecution.language.application.find;

import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.language.domain.repository.LanguageRepository;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesResponse;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdResponse;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.LanguageId;

import java.util.List;
import java.util.Optional;

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

    public FindLanguageByIdResponse findById(LanguageId languageId) {
        Optional<Language> language = languageRepository.findById(languageId);

        if (language.isEmpty()) {
            throw new EntityNotFoundException("Language not found");
        }

        return new FindLanguageByIdResponse(language.get());
    }
}
