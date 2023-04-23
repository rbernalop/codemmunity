package org.rbernalop.apitest.test.application.check;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsQuery;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsQueryMother;
import org.rbernalop.apitest.shared.application.test.check.CheckScriptTestsResponse;
import org.rbernalop.apitest.test.domain.aggregate.Test;
import org.rbernalop.apitest.test.domain.aggregate.TestMother;
import org.rbernalop.apitest.test.domain.port.TestRepository;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckScriptTestHandlerTests extends UnitTestCase {
    @Mock
    private TestRepository testRepository;

    @InjectMocks
    private CheckScriptTestsHandler checkScriptTestsHandler;

    @org.junit.jupiter.api.Test
    void should_check_script_tests() {
        // GIVEN
        CheckScriptTestsQuery query = CheckScriptTestsQueryMother.fromLanguageName("sample");
        List<Test> tests = List.of(TestMother.fromChallengeIdAndLanguageName(query.getChallengeId(), query.getLanguageName()));

        ChallengeId challengeId = new ChallengeId(query.getChallengeId());
        LanguageName languageName = new LanguageName(query.getLanguageName());
        when(testRepository.findByChallengeIdAndLanguageName(challengeId, languageName)).thenReturn(tests);

        // WHEN
        CheckScriptTestsResponse response = assertDoesNotThrow(() -> checkScriptTestsHandler.handle(query));

        // THEN
        verify(testRepository, times(1)).findByChallengeIdAndLanguageName(challengeId, languageName);
        assertTrue(response.isPassed());
        assertEquals(1, response.getResults().size());
    }
}