package org.rbernalop.apiexecution.language.application.find.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apiexecution.language.domain.aggregate.Language;
import org.rbernalop.apiexecution.language.domain.aggregate.LanguageMother;
import org.rbernalop.apiexecution.language.domain.port.LanguageRepository;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdMother;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdQuery;
import org.rbernalop.apiexecution.shared.application.language.find.by_id.FindLanguageByIdResponse;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindLanguageByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private LanguageRepository languageRepository;



    @InjectMocks
    private FindLanguageByIdQueryHandler findLanguageByIdQueryHandler;

    @Test
    void should_find_language_by_id() {
        // GIVEN
        Language language = LanguageMother.random();
        assertNotNull(language.getId());
        FindLanguageByIdQuery query = FindLanguageByIdMother.fromLanguageId(language.getId().getValue());
        when(languageRepository.findById(new LanguageId(query.getId()))).thenReturn(Optional.of(language));

        // WHEN
        FindLanguageByIdResponse response = assertDoesNotThrow(() -> findLanguageByIdQueryHandler.handle(query));

        // THEN
        assertEquals(language.getId().getValue(), response.getId());
        assertEquals(language.getName(), response.getName());
    }

    @Test
    void should_throw_EntityNotFoundException_when_language_not_found() {
        // GIVEN
        FindLanguageByIdQuery query = FindLanguageByIdMother.random();
        when(languageRepository.findById(new LanguageId(query.getId()))).thenReturn(Optional.empty());

        // WHEN
        EntityNotFoundException exception =
            assertThrows(EntityNotFoundException.class, () -> findLanguageByIdQueryHandler.handle(query));

        // THEN
        assertEquals("Language not found", exception.getMessage());
    }
}