package org.rbernalop.apiexecution.language.application.find.all;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.language.domain.aggregate.LanguageMother;
import org.rbernalop.apiexecution.language.domain.repository.LanguageRepository;
import org.rbernalop.apiexecution.shared.application.language.find.LanguageResponse;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesQuery;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesQueryMother;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesResponse;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindAllLanguagesQueryHandlerTest extends UnitTestCase {


    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private FindAllLanguagesQueryHandler findAllLanguagesQueryHandler;

    @Test
    void should_find_all_languages() {
        // GIVEN
        FindAllLanguagesQuery query = FindAllLanguagesQueryMother.random();
        List<Language> expectedLanguages = LanguageMother.randomList();
        when(languageRepository.findAll()).thenReturn(expectedLanguages);

        // WHEN
        FindAllLanguagesResponse response = assertDoesNotThrow(() -> findAllLanguagesQueryHandler.handle(query));

        // THEN
        assertEquals(expectedLanguages.size(), response.getLanguages().size());
        Language expectedLanguage = expectedLanguages.get(0);
        LanguageResponse languageResponse = response.getLanguages().get(0);
        assertNotNull(expectedLanguage.getId());
        assertEquals(expectedLanguage.getId().getValue(), languageResponse.getId());
        assertEquals(expectedLanguage.getName(), languageResponse.getName());
        verify(languageRepository, times(1)).findAll();
    }
}