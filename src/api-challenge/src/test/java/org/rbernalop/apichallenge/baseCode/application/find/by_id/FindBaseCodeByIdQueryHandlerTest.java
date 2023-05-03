package org.rbernalop.apichallenge.baseCode.application.find.by_id;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCodeMother;
import org.rbernalop.apichallenge.baseCode.domain.port.BaseCodeRepository;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdQuery;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdQueryMother;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdResponse;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindBaseCodeByIdQueryHandlerTest extends UnitTestCase {
    @Mock
    private BaseCodeRepository baseCodeRepository;

    @InjectMocks
    private FindBaseCodeByIdQueryHandler findBaseCodeByIdQueryHandler;

    @Test
    void should_find_base_code_by_id() {
        // GIVEN
        BaseCode baseCode = BaseCodeMother.random();
        FindBaseCodeByIdQuery findBaseCodeByIdQuery = FindBaseCodeByIdQueryMother.fromBaseCode(baseCode);

        when(baseCodeRepository.findById(baseCode.getBaseCodeId())).thenReturn(Optional.of(baseCode));

        // WHEN
        FindBaseCodeByIdResponse findBaseCodeByIdResponse =
                assertDoesNotThrow(() -> findBaseCodeByIdQueryHandler.handle(findBaseCodeByIdQuery));

        // THEN
        verify(baseCodeRepository, times(1)).findById(baseCode.getBaseCodeId());
        assertEquals(baseCode.getBaseCodeId().getChallengeId(), findBaseCodeByIdResponse.getChallengeId());
        assertEquals(baseCode.getBaseCodeId().getLanguageName(), findBaseCodeByIdResponse.getLanguageName());
        assertEquals(baseCode.getContent(), findBaseCodeByIdResponse.getCode());
    }

    @Test
    void should_throw_EntityNotFoundException_when_base_code_not_found() {
        // GIVEN
        BaseCode baseCode = BaseCodeMother.random();
        FindBaseCodeByIdQuery findBaseCodeByIdQuery = FindBaseCodeByIdQueryMother.fromBaseCode(baseCode);

        when(baseCodeRepository.findById(baseCode.getBaseCodeId())).thenReturn(Optional.empty());

        // WHEN
        EntityNotFoundException entityNotFoundException =
                assertThrows(EntityNotFoundException.class, () -> findBaseCodeByIdQueryHandler.handle(findBaseCodeByIdQuery));

        // THEN
        verify(baseCodeRepository, times(1)).findById(baseCode.getBaseCodeId());
        assertEquals("Base code for challenge " + baseCode.getBaseCodeId().getChallengeId() + " and language " + baseCode.getBaseCodeId().getLanguageName() + " not found", entityNotFoundException.getMessage());
    }
}